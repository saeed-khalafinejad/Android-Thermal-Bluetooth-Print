# Android-Thermal-Bluetooth-Print
The app developed by Saeed Khalafinejad from Kasabeh Accounting Software Group for printing from Android to Bluetooth thermal printers. (Kasabeh means businessmen).

# Description

This app demonstrates how you can print from an Android device to portable Bluetooth thermal printers.
The library for printing is located at the `org.kasabeh.androidprint.lib` package. You can easily reuse the 
package in your own project. Some notes about the project:

* The app works with all ESC/POS commands friendly printers.
* We have tested the app on Bixolon, Woosim, Sewoo, Baby and Rongta printers.
* Based on the implementation, the app should support almost all Bluetooth printers.
* If you find any printer that is not supported by the app then you need to extends the `WoosimPrnMng` class
  based on the unsupported printer documentations.
* Besides English characters, the app also supports Farsi/Arabic characters.
* For other languages, based on the font installed on the device, you need to implement your owner character mapper
  (Similar to the `WCharMapperCT42` class).
* The printing library here is used in [Kasabeh Pocket Accounting Software](https://cafebazaar.ir/app/ilia.anrdAcunt.ui?l=en) for printing invoices and person's transactions (Kasabeh means businessmen in Farsi). See this
  Gist: [the application of the library package in a real project](https://gist.github.com/saeed-khalafinejad/d1ca60aca007474aa7b23f036113020d).

# How to Print

First, implement the `IPrintToPrinter` interface:

    public class TestPrinter implements IPrintToPrinter {
        ...
        @Override
        public void printContent(WoosimPrnMng prnMng) {
            prnMng.printStr("Header", 2, WoosimCmd.ALIGN_CENTER);
            prnMng.printStr("1-First line", 1, WoosimCmd.ALIGN_LEFT);
            prnMng.printStr("2-Second line", 1, WoosimCmd.ALIGN_LEFT);
            prnMng.printNewLine();
            prnMng.printStr("Footer", 1, WoosimCmd.ALIGN_CENTER);
        }
        ...
    }

Then, instantiate the print manager class to make the Bluetooth connection and to issue the print command:

    ...
    IPrintToPrinter testPrinter = new TestPrinter(this);
    //The below command makes a connection to the printer and after successful connection issues the print command.
    mPrnMng = printerFactory.createPrnMng(this, blutoothAddr, testPrinter);
    ...

# App Screenshot
To test the app, just choose your printer brand and press the print button.

![Android portable Bluetooth thermal printer app](http://opensource.kasabeh.org/images/Blutooth-portable-thermal-printer.png)

## More information
Please contact us via Email or visit our websites for more information. Since the websites are in Farsi, Google Translate service can be helpful for translation however it is not always accurate.
* info@kasabeh.org
* [http://opensource.kasabeh.org](http://opensource.kasabeh.org)
* [http://mobile.kasabeh.org](http://mobile.kasabeh.org)
* [https://kasabeh.org](https://kasabeh.org)
* Kasabeh Pocket Accounting app on the CafeBazaar Iranian Android market: [https://cafebazaar.ir/app/ilia.anrdAcunt.ui?l=en](https://cafebazaar.ir/app/ilia.anrdAcunt.ui?l=en)
