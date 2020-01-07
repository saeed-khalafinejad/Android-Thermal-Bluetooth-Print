package org.kasabeh.androidprint.lib;

import java.util.ArrayList;

/**
 * Map Farsi characters bases on the Woosim default Arabic font.
 * This is the base class for other mappers.
 * @author Saeed
 *
 */
public class WCharMapperCT42 {
	
	private int getCellNO(String s, int ix){
		char c = s.charAt(ix);
		if (c>=48 && c<=57) return getCellNONum(c);
		if (c>=0x0660 && c<=0x0669) return getCellNOArabNO1(c);
		if (c>=0x06F0 && c<=0x06F9) return getCellNOArabNO2(c);
		if (c>=0 && c<=127) {
			return c;
		}
					
		boolean preConn = 
				(ix!=0) && (!BidiStringBreaker.isSpecialChar(s.charAt(ix-1))) && (connectToNext(s.charAt(ix-1)));
		boolean postConn = 
				(ix!=s.length()-1) && (!BidiStringBreaker.isSpecialChar(s.charAt(ix+1))) &&  (connectToNext(c));
		if (!preConn && !postConn) return isolatedForm(c);
		if (!preConn && postConn) return initialForm(c);
		if (preConn && postConn) return medialForm(c);
		//(preConn && !postConn) 
		return finalForm(c);
	}

	private int getCellNOArabNO2(char c) {
		return c - 1728;
		//return c - 1728 + 187;//return Farsi NO
	}

	private int getCellNOArabNO1(char c) {
		return c - 1584;
		//return c - 1584 + 187;//return Farsi NO
	}

	private int getCellNONum(char c) {
		return c;
		//return c + 187;//return Farsi NO
	}

	protected int isolatedForm(char c) {
		if (c=='ا') return 0x80;
		if (c=='آ') return 0x80;
		if (c=='ب') return 0xbc;
		if (c=='پ') return 0xbc;
		if (c=='ت') return 0xbd;
		if (c=='ث') return 0xbe;
		if (c=='ج') return 0xbf;
		if (c=='چ') return 0xbf;
		if (c=='ح') return 0xc0;
		if (c=='خ') return 0xc1;
		if (c=='د') return 0xb4;
		if (c=='ذ') return 0xb5;
		if (c=='ر') return 0xb6;
		if (c=='ز') return 0xb7;
		if (c=='ژ') return 0xb7;
		if (c=='س') return 0xc2;
		if (c=='ش') return 0xc3;
		if (c=='ص') return 0xc4;
		if (c=='ض') return 0xc5;
		if (c=='ط') return 0xc6;
		if (c=='ظ') return 0xc7;
		if (c=='ع') return 0xa4;
		if (c=='غ') return 0xa5;
		if (c=='ف') return 0xa6;
		if (c=='ق') return 0xa7;
		if (c=='ک') return 0xa8;
		if (c=='ك') return 0xa8;
		if (c=='گ') return 0xd7;
		if (c=='ل') return 0xa9;
		if (c=='م') return 0xaa;
		if (c=='ن') return 0xab;
		if (c=='و') return 0xe5;
		if (c=='ه') return 0xd5;
		if (c=='ی') return 0xad;
		if (c=='ي') return 0xad;
		return otherChars(c);
	}

	protected int initialForm(char c) {
		if (c=='ا') return 0x80;
		if (c=='آ') return 0x80;
		if (c=='ب') return 0xae;
		if (c=='پ') return 0xd9;
		if (c=='ت') return 0xaf;
		if (c=='ث') return 0xb0;
		if (c=='ج') return 0xb1;
		if (c=='چ') return 0xb1;
		if (c=='ح') return 0xb2;
		if (c=='خ') return 0xb3;
		if (c=='د') return 0xb4;
		if (c=='ذ') return 0xb5;
		if (c=='ر') return 0xb6;
		if (c=='ز') return 0xb7;
		if (c=='ژ') return 0xb7;
		if (c=='س') return 0x85;
		if (c=='ش') return 0x86;
		if (c=='ص') return 0x87;
		if (c=='ض') return 0x88;
		if (c=='ط') return 0x89;
		if (c=='ظ') return 0x8a;
		if (c=='ع') return 0xd3;
		if (c=='غ') return 0xdd;
		if (c=='ف') return 0xde;
		if (c=='ق') return 0xdf;
		if (c=='ک') return 0xe0;
		if (c=='ك') return 0xe0;		
		if (c=='گ') return 0xd7;
		if (c=='ل') return 0xe1;
		if (c=='م') return 0xe2;
		if (c=='ن') return 0xe3;
		if (c=='و') return 0xe5;
		if (c=='ه') return 0xe4;
		if (c=='ی') return 0xe6;
		if (c=='ي') return 0xe6;
		return otherChars(c);		
	}
	
	protected int otherChars(char c) {
		//TODO more characters such as Arabic question mark (?) may be added here.		
		if (c=='،') return 0xf8;
		return 0x5f;
	}

	protected int medialForm(char c) {
		if (c=='ا') return 0xbb;
		if (c=='آ') return 0xbb;
		if (c=='ب') return 0xe9;
		if (c=='پ') return 0x97;
		if (c=='ت') return 0xea;
		if (c=='ث') return 0xf7;
		if (c=='ج') return 0xb1;
		if (c=='چ') return 0xb1;
		if (c=='ح') return 0xb2;
		if (c=='خ') return 0xb3;
		if (c=='د') return 0xb4;
		if (c=='ذ') return 0xb5;
		if (c=='ر') return 0xb6;
		if (c=='ز') return 0xb7;
		if (c=='ژ') return 0xb7;
		if (c=='س') return 0x85;
		if (c=='ش') return 0x86;
		if (c=='ص') return 0x87;
		if (c=='ض') return 0x88;
		if (c=='ط') return 0x89;
		if (c=='ظ') return 0x8a;
		if (c=='ع') return 0x8b;
		if (c=='غ') return 0x8c;
		if (c=='ف') return 0x8d;
		if (c=='ق') return 0x8e;
		if (c=='ک') return 0x8f;
		if (c=='ك') return 0x8f;		
		if (c=='گ') return 0x96;
		if (c=='ل') return 0x90;
		if (c=='م') return 0x91;
		if (c=='ن') return 0x92;
		if (c=='و') return 0xe5;
		if (c=='ه') return 0x93;
		if (c=='ی') return 0x95;
		if (c=='ي') return 0x95;
		return otherChars(c);
	}
	
	protected int finalForm(char c) {
		if (c=='ا') return 0xbb;
		if (c=='آ') return 0xbb;
		if (c=='ب') return 0xbc;
		if (c=='پ') return 0xbc;
		if (c=='ت') return 0xbd;
		if (c=='ث') return 0xbe;
		if (c=='ج') return 0xbf;
		if (c=='چ') return 0xbf;
		if (c=='ح') return 0xc0;
		if (c=='خ') return 0xc1;
		if (c=='د') return 0xb4;
		if (c=='ذ') return 0xb5;
		if (c=='ر') return 0xb6;
		if (c=='ز') return 0xb7;
		if (c=='ژ') return 0xb7;
		if (c=='س') return 0xc2;
		if (c=='ش') return 0xc3;
		if (c=='ص') return 0xc4;
		if (c=='ض') return 0xc5;
		if (c=='ط') return 0xc6;
		if (c=='ظ') return 0xc7;
		if (c=='ع') return 0xc9;
		if (c=='غ') return 0xca;
		if (c=='ف') return 0xa6;
		if (c=='ق') return 0xa7;
		if (c=='ک') return 0x8f;
		if (c=='ك') return 0x8f;		
		if (c=='گ') return 0x96;
		if (c=='ل') return 0xd1;
		if (c=='م') return 0xaa;
		if (c=='ن') return 0xab;
		if (c=='و') return 0xe5;
		if (c=='ه') return 0xac;
		if (c=='ی') return 0xdc;
		if (c=='ي') return 0xdc;
		return otherChars(c);
	}

	private boolean connectToNext(char preChar) {		
		return !(preChar=='آ' || preChar=='ا' || preChar=='د' || preChar=='ذ' || 
			   preChar=='ر' || preChar=='ز' || preChar=='ژ' || 
			   preChar=='و');
	}

	/**
	 * This method assumes that the prevailing direction is RTL.
	 * @param string to be encoded in the Woosim code
	 * tables (i.e. 42:WoosimCmd.CT_ARABIC_FARSI or 43:CT_ARABIC_FORMS_B or ...).
	 * @return Integer codes for the string.
	 */
	public ArrayList<Integer> getCodes(String string) {
		BidiStringBreaker bidiBreaker = new BidiStringBreaker();
        ArrayList<String> brokenDown = bidiBreaker.breakDown(string);
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = brokenDown.size()-1; i>=0; i--) {
        	String s = brokenDown.get(i);
        	if (bidiBreaker.getKind(s, 0)==BidiStringBreaker.CFarsi){
        		res.addAll(getSubCodesFarsi(s));				
        	}else{
        		res.addAll(getSubCodesEng_Num(s));
        	}
		}        
        return res;            
	}

	private ArrayList<Integer> getSubCodesFarsi(String s) {
		ArrayList<Integer> wCodes = new ArrayList<Integer>();		
    	for (int i = s.length()-1;i>=0;i--){
    		wCodes.add(getCellNO(s, i));
    	}		
    	return wCodes;	
    }

	private ArrayList<Integer> getSubCodesEng_Num(String s) {
		ArrayList<Integer> wCodes = new ArrayList<Integer>();		
    	for (int i = 0;i<s.length();i++){
    		wCodes.add(getCellNO(s, i));
    	}		
    	return wCodes;	
    }
	
}
