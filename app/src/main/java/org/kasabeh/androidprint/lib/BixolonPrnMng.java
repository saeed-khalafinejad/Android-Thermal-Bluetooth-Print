package org.kasabeh.androidprint.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.util.ByteArrayBuffer;

import java.util.ArrayList;
import java.util.List;
import org.kasabeh.androidprint.R;
import org.kasabeh.androidprint.utils.ImageGallaryMng;
import org.kasabeh.androidprint.utils.printerFactory;


/**
 * Created by Saeed on 01/06/2018.
 *
 * Note that the BixolonPrnMng class can be used to print to almost all printer devices.
 * For example, you can directly instantiate the BixolonPrnMng class and print to
 * Bixolon, Sewoo or Baby thermal printers.
 */
public class BixolonPrnMng extends WoosimPrnMng {

    public BixolonPrnMng(Context c, String deviceAddr, IPrintToPrinter prnToWoosim) {
        super(c, deviceAddr, prnToWoosim);
    }

    @Override
    protected void simplePrintStr(boolean mEmphasis, boolean mUnderline,
                                  int mCharsize, int mJustification, String strLine) {
        WCharMapperCT42 wm = printerFactory.getActiveCharMapper(contx);
        ArrayList<Integer> wCodes = wm.getCodes(strLine);

        ByteArrayBuffer buffer = new ByteArrayBuffer(1024);

        if (mEmphasis) {
            byte[] cmd_emphasis = new byte[]{27, 69, (byte) 0x01};
            buffer.append(cmd_emphasis, 0, cmd_emphasis.length);
        }

        mCharsize-=1;
        if (mCharsize!=0) {
            byte charSize = (byte) (mCharsize | (mCharsize << 4));
            byte[] cmd_size = new byte[]{29, 33, charSize};
            buffer.append(cmd_size, 0, cmd_size.length);
        } else {
            byte[] cmd_size = new byte[]{29, 33, 0};
            buffer.append(cmd_size, 0, cmd_size.length);
        }

        byte[] cmd_justification = new byte[]{27, 97, (byte) mJustification};
        buffer.append(cmd_justification, 0, cmd_justification.length);

        for (Integer i : wCodes) {
            buffer.append(i);
        }

        buffer.append('\n');
        sendData(buffer.toByteArray());
    }

    @Override
    public void printBitmap(String picPath, int maxWidth) {
        Bitmap bmp = ImageGallaryMng.getBitmap(picPath);
        if (bmp == null) {
            Toast.makeText(contx, R.string.pic_load_error, Toast.LENGTH_LONG).show();
            return;
        }
        byte[] command = Utils.decodeBitmap(bmp);

        byte[] cmd_justification = new byte[]{27, 97, (byte) 1};
        sendData(cmd_justification);
        sendData(command);

    }


    //------------------------------------------------------
    public static class Utils {

        private static String hexStr = "0123456789ABCDEF";
        private static String[] binaryArray = { "0000", "0001", "0010", "0011",
                "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011",
                "1100", "1101", "1110", "1111" };

        public static byte[] decodeBitmap(Bitmap bmp){
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();

            List<String> list = new ArrayList<String>(); //binaryString list
            StringBuffer sb;


            int bitLen = bmpWidth / 8;
            int zeroCount = bmpWidth % 8;

            String zeroStr = "";
            if (zeroCount > 0) {
                bitLen = bmpWidth / 8 + 1;
                for (int i = 0; i < (8 - zeroCount); i++) {
                    zeroStr = zeroStr + "0";
                }
            }

            for (int i = 0; i < bmpHeight; i++) {
                sb = new StringBuffer();
                for (int j = 0; j < bmpWidth; j++) {
                    int color = bmp.getPixel(j, i);

                    int r = (color >> 16) & 0xff;
                    int g = (color >> 8) & 0xff;
                    int b = color & 0xff;

                    // if color close to white，bit='0', else bit='1'
                    if (r > 160 && g > 160 && b > 160)
                        sb.append("0");
                    else
                        sb.append("1");
                }
                if (zeroCount > 0) {
                    sb.append(zeroStr);
                }
                list.add(sb.toString());
            }

            List<String> bmpHexList = binaryListToHexStringList(list);
            String commandHexString = "1D763000";
            String widthHexString = Integer
                    .toHexString(bmpWidth % 8 == 0 ? bmpWidth / 8
                            : (bmpWidth / 8 + 1));
            if (widthHexString.length() > 2) {
                Log.e("decodeBitmap error", " width is too large");
                return null;
            } else if (widthHexString.length() == 1) {
                widthHexString = "0" + widthHexString;
            }
            widthHexString = widthHexString + "00";

            String heightHexString = Integer.toHexString(bmpHeight);
            if (heightHexString.length() > 2) {
                Log.e("decodeBitmap error", " height is too large");
                return null;
            } else if (heightHexString.length() == 1) {
                heightHexString = "0" + heightHexString;
            }
            heightHexString = heightHexString + "00";

            List<String> commandList = new ArrayList<String>();
            commandList.add(commandHexString+widthHexString+heightHexString);
            commandList.addAll(bmpHexList);

            return hexList2Byte(commandList);
        }

        public static List<String> binaryListToHexStringList(List<String> list) {
            List<String> hexList = new ArrayList<String>();
            for (String binaryStr : list) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < binaryStr.length(); i += 8) {
                    String str = binaryStr.substring(i, i + 8);

                    String hexString = myBinaryStrToHexString(str);
                    sb.append(hexString);
                }
                hexList.add(sb.toString());
            }
            return hexList;

        }

        public static String myBinaryStrToHexString(String binaryStr) {
            String hex = "";
            String f4 = binaryStr.substring(0, 4);
            String b4 = binaryStr.substring(4, 8);
            for (int i = 0; i < binaryArray.length; i++) {
                if (f4.equals(binaryArray[i]))
                    hex += hexStr.substring(i, i + 1);
            }
            for (int i = 0; i < binaryArray.length; i++) {
                if (b4.equals(binaryArray[i]))
                    hex += hexStr.substring(i, i + 1);
            }

            return hex;
        }

        public static byte[] hexList2Byte(List<String> list) {
            List<byte[]> commandList = new ArrayList<byte[]>();

            for (String hexStr : list) {
                commandList.add(hexStringToBytes(hexStr));
            }
            byte[] bytes = sysCopy(commandList);
            return bytes;
        }

        public static byte[] hexStringToBytes(String hexString) {
            if (hexString == null || hexString.equals("")) {
                return null;
            }
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            byte[] d = new byte[length];
            for (int i = 0; i < length; i++) {
                int pos = i * 2;
                d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }
            return d;
        }

        public static byte[] sysCopy(List<byte[]> srcArrays) {
            int len = 0;
            for (byte[] srcArray : srcArrays) {
                len += srcArray.length;
            }
            byte[] destArray = new byte[len];
            int destLen = 0;
            for (byte[] srcArray : srcArrays) {
                System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
                destLen += srcArray.length;
            }
            return destArray;
        }

        private static byte charToByte(char c) {
            return (byte) "0123456789ABCDEF".indexOf(c);
        }
    }
    //------------------------------------------------------

}
