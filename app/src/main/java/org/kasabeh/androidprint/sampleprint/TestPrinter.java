package org.kasabeh.androidprint.sampleprint;

import android.content.Context;
import android.widget.Toast;

import com.woosim.printer.WoosimCmd;

import org.kasabeh.androidprint.R;
import org.kasabeh.androidprint.lib.IPrintToPrinter;
import org.kasabeh.androidprint.lib.WoosimPrnMng;
import org.kasabeh.androidprint.lib.printerWordMng;
import org.kasabeh.androidprint.utils.printerFactory;

public class TestPrinter implements IPrintToPrinter {

    private Context context;

    public TestPrinter(Context context){
        this.context = context;
    }

    @Override
    public void printContent(WoosimPrnMng prnMng) {
        printerWordMng wordMng = printerFactory.createPaperMng(context);
        prnMng.printStr("Header", 2, WoosimCmd.ALIGN_CENTER);
        prnMng.printStr("1-First line", 1, WoosimCmd.ALIGN_LEFT);
        prnMng.printStr(wordMng.getHorizontalUnderline());
        prnMng.printStr(wordMng.autoWordWrap("2-Second line that is very very long line to check word wrap"),
                        1, WoosimCmd.ALIGN_LEFT);
        prnMng.printStr(wordMng.getHorizontalUnderline());
        prnMng.printStr("3-Third line",1, WoosimCmd.ALIGN_LEFT);
        prnMng.printNewLine();
        prnMng.printStr("Footer",1, WoosimCmd.ALIGN_CENTER);

        //You can also print a logo
        //prnMng.printBitmap("/sdcard/test/001.png", IBixolonCanvasConst.CMaxChars_2Inch);

        //Any finalization, you can call it here or any where in your running activity.
        printEnded(prnMng);
    }

    @Override
    public void printEnded(WoosimPrnMng prnMng) {
        //Do any finalization you like after print ended.
        if (prnMng.printSucc()){
            Toast.makeText(context, R.string.print_succ, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, R.string.print_error, Toast.LENGTH_LONG).show();
        }
    }
}
