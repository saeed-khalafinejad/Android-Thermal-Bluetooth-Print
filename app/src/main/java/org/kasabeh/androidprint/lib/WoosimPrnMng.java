package org.kasabeh.androidprint.lib;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.woosim.printer.WoosimCmd;
import com.woosim.printer.WoosimImage;
import com.woosim.printer.WoosimService;

import org.apache.http.util.ByteArrayBuffer;
import org.kasabeh.androidprint.R;
import org.kasabeh.androidprint.utils.ImageGallaryMng;
import org.kasabeh.androidprint.utils.PrefMng;
import org.kasabeh.androidprint.utils.printerFactory;

import java.util.ArrayList;


public class WoosimPrnMng{

    public static final int MESSAGE_DEVICE_NAME = 1;
    public static final int MESSAGE_TOAST = 2;
    public static final int MESSAGE_READ = 3;
    
    // Key names received from the BluetoothPrintService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
	//public static final int CActiveCodeTable = WoosimCmd.CT_ARABIC_FARSI;
    //public static final int CActiveCodeTable = WoosimCmd.CT_ARABIC_FORMS_B;
    
    private static BluetoothPrintService mPrintService = null;
    private static WoosimService mWoosim = null;
    private String mDeviceAddr = "";
    private BluetoothDevice device;
    protected Context contx;
	private IPrintToPrinter mPrintToPrinter;
      
    public void releaseAllocatoins(){    
    	if (mPrintService!=null){
    		new Thread(new Runnable() {			
				@Override
				public void run() {					
					mPrintService.stop();
					while (mPrintService.getState()!=BluetoothPrintService.STATE_NONE) {};
					mPrintService = null;
				}
			}).run();    		
    	}
    	    	
    	mWoosim = null;
    }
    
    public WoosimPrnMng(Context c, String deviceAddr, IPrintToPrinter prnToWoosim) {
    	mDeviceAddr = deviceAddr;
    	contx = c;
    	mPrintToPrinter = prnToWoosim;
		PrefMng.saveDeviceAddr(contx, "");
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        device = mBluetoothAdapter.getRemoteDevice(mDeviceAddr);

        if (mWoosim==null) mWoosim = new WoosimService(mHandler);
        if (mPrintService==null){
        	mPrintService = new BluetoothPrintService(contx, mHandler);
        }
        
        if (mPrintService.getState() == BluetoothPrintService.STATE_NONE){
            	mPrintService.start();
        }
        
        if (mPrintService.getState() == BluetoothPrintService.STATE_LISTEN){        
            mPrintService.connect(device, false);        	
        }else{
        	if (mPrintService.getState() == BluetoothPrintService.STATE_CONNECTED)
        		printInfo();
        }
        
	}

    /**
     * Print string in ALIGN_RIGHT mode.
     * @param string
     */
    public void printStr(String string){
    	printStr(string, PrefMng.getBoldPrinting(contx), false, 1, WoosimCmd.ALIGN_RIGHT);
    }
    
    public void printStr(String string, 
    		int mCharsize, int mJustification){
    	printStr(string, PrefMng.getBoldPrinting(contx), false, mCharsize, mJustification);
    }
    
    public void printStr(String string, boolean mEmphasis, boolean mUnderline, 
    		int mCharsize, int mJustification) {    	    	
    	String[] arrLines = string.split(WoosimPrnMng.getSysLineSeparator());
    	for (String strLine : arrLines) {
    		simplePrintStr(mEmphasis, mUnderline, mCharsize, mJustification,
					strLine);					
		}
	}


	/**
	 *
	 * @param mEmphasis
	 * @param mUnderline
	 * @param mCharsize 1:normal, 2:large
	 * @param mJustification 0:left, 1:center, 2:right
	 * @param strLine
	 */
	 protected void simplePrintStr(boolean mEmphasis, boolean mUnderline,
			int mCharsize, int mJustification, String strLine) {
		WCharMapperCT42 wm = printerFactory.getActiveCharMapper(contx);
		ArrayList<Integer> wCodes = wm.getCodes(strLine);

		ByteArrayBuffer buffer = new ByteArrayBuffer(1024);
		    	
		byte[] cmd_style = WoosimCmd.setTextStyle(mEmphasis, mUnderline, false, mCharsize, mCharsize);
		byte[] cmd_justification = WoosimCmd.setTextAlign(mJustification);
		byte[] cmd_CodeTable = WoosimCmd.setCodeTable(WoosimCmd.MCU_RX,
				PrefMng.getWoosimCodeTbl(), WoosimCmd.FONT_LARGE);
		byte[] cmd_default_CT = WoosimCmd.setCodeTable(WoosimCmd.MCU_RX, WoosimCmd.CT_CP437, WoosimCmd.FONT_LARGE);
		byte[] cmd_prn = WoosimCmd.printData();
		    	    	
		buffer.append(cmd_CodeTable, 0, cmd_CodeTable.length);
		buffer.append(cmd_justification, 0, cmd_justification.length);
		buffer.append(cmd_style, 0, cmd_style.length);

		for (Integer i : wCodes) {
			buffer.append(i);
		}    		
		   	    	
		buffer.append(cmd_default_CT, 0, cmd_default_CT.length);
		buffer.append(cmd_prn, 0, cmd_prn.length);
		
		sendData(WoosimCmd.initPrinter());
		sendData(buffer.toByteArray());
	}

	public void printNewLine(){
		simplePrintStr(false, false, 1, WoosimCmd.ALIGN_RIGHT, "\n");
	}
	public static String getSysLineSeparator() {
		return System.getProperty("line.separator");
	}

	protected void sendData(byte[] data) {
        if (mPrintService.getState() != BluetoothPrintService.STATE_CONNECTED) {
            Toast.makeText(contx, R.string.not_connected, Toast.LENGTH_LONG).show();
            return;
        }
        if (data.length > 0) 
        	mPrintService.write(data);
    }
    
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_DEVICE_NAME:
                String mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                Toast.makeText(contx, contx.getString(R.string.connected) + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                printInfo();
                break;
            case MESSAGE_TOAST:
                Toast.makeText(contx, msg.getData().getInt(TOAST), Toast.LENGTH_SHORT).show();
                break;
            case MESSAGE_READ:
                mWoosim.processRcvData((byte[])msg.obj, msg.arg1);
                break;
            case WoosimService.MESSAGE_PRINTER:
            	Toast.makeText(contx, "MSR message", Toast.LENGTH_SHORT).show();
            	break;
            }
        }
    };

	public boolean printSucc(){
		return mPrintService.getState() == BluetoothPrintService.STATE_CONNECTED;
	}
	
	public String getDeviceAddr() {
		return mDeviceAddr;
	}
    
	private void printInfo() {
		mPrintToPrinter.printContent(WoosimPrnMng.this);
	}

	public void printTable(ByteArrayBuffer buffer) {
		sendData(WoosimCmd.initPrinter());
		sendData(buffer.toByteArray());
	}
	
	/**
	 * Print bitmap image aligned in center.
	 * @param picPath
	 * @param maxWidth
	 */
	public void printBitmap(String picPath, int maxWidth){
    	Bitmap bmp = ImageGallaryMng.getBitmap(picPath);
    	if (bmp == null) {
    		Toast.makeText(contx, R.string.pic_load_error, Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	int w = bmp.getWidth();
    	if (w>maxWidth) w = maxWidth;
    	int x = (maxWidth-w)/2;
    	byte[] data = WoosimImage.printBitmap(x, 5, w, bmp.getHeight(), bmp);
    	bmp.recycle();
    	
    	sendData(WoosimCmd.setPageMode());
    	sendData(data);
    	sendData(WoosimCmd.PM_setStdMode());
    }
}
