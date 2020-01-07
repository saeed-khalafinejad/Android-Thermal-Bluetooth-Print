package org.kasabeh.androidprint.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.woosim.printer.WoosimCmd;

public class PrefMng {

    private static final String PREF_NAME = "org.kasabeh.androidprint";
    private static final String PREF_DEV_ADDR = "PrefMng.PREF_DEVADDR";
    private static final String PREF_PRINTER = "PrefMng.PREF_PRINTER";
    public static final int PRN_WOOSIM_SELECTED = 1;
    public static final int PRN_BIXOLON_SELECTED = 2;
    public static final int PRN_OTHER_PRINTERS_SELECTED = 3;
    public static final int PRN_RONGTA_SELECTED = 4;

    public static int getActivePrinter(Context c) {
        SharedPreferences pref = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_PRINTER, PRN_WOOSIM_SELECTED);
    }

    public static void saveActivePrinter(Context context, int printerName) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_PRINTER, printerName);
        editor.commit();
    }

    public static void saveDeviceAddr(Context context, String newAddr) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_DEV_ADDR, newAddr);
        editor.commit();
    }

    /**
     * You can use the getDeviceAddr method to bypass DeviceListActivity.
     * @param context
     * @return If the return value is an empty string, it means no printer Bluetooth
     * address already is saved. In this case you MUST first run DeviceListActivity.
     * If the return value is not empty then you can bypass loading DeviceListActivity.
     * The best place to save the Bluetooth address is in the IPrintToPrinter.printEnded
     * method when the print operation is ended successfully.
     */
    public static String getDeviceAddr(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_DEV_ADDR, "");
    }

    public static boolean getBoldPrinting(Context contx) {
        return false;
    }

    /**
     * This method is specific to the Woosim printers only. In other words,
     * you can choose which font to use on the Woosim printers.
     * @return The code table for printing.
     */
    public static int getWoosimCodeTbl() {
        /*Based on the installed font on the device you can return
          WoosimCmd.CT_IRAN_SYSTEM or other code tables.*/
        return WoosimCmd.CT_ARABIC_FARSI;//It also supports English.
    }

}
