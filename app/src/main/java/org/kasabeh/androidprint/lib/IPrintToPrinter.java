package org.kasabeh.androidprint.lib;

public interface IPrintToPrinter {

	/**
	 *
	 * @param prnMng The WoosimPrnMng class is the base class for printing to
	 *               all potable printers. The parameter can be WoosimPrnMng or
	 *               any of its descendent classes such BixolonPrnMng, RongtaPrnMng, etc.
	 */
	void printContent(WoosimPrnMng prnMng);
	/**
	 *
	 * @param prnMng The WoosimPrnMng class is the base class for printing to
	 *               all potable printers. The parameter can be WoosimPrnMng or
	 *               any of its descendent classes such BixolonPrnMng, RongtaPrnMng, etc.	 *
	 */
	void printEnded(WoosimPrnMng prnMng);
}
