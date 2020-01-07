package org.kasabeh.androidprint.lib;

import java.util.ArrayList;

/**
 * This class is the "naive" and "handy" implementation of Unicode Bidirectional Algorithm.
 * The algorithm is useful for printing Farsi phrases.  
 * @author Saeed
 *
 */
public class BidiStringBreaker {

	public static final int CFarsi = 2;
	public static final int CEnglish_Number = 3;
	public static final int CUndef = 4;
	private static final int CBreaker = 5;
	private static final char CCharForceBreaker = 31;
		
	public ArrayList<String> breakDown(String s){
		if (s==null || s.length()==0) return errArray();
		ArrayList<String> res = new ArrayList<String>();
		
		int ix = 0;
		int kind = getKind(s, 0);
		int i = 1;
		while(i<s.length()){
			int newKind = getKind(s, i);
			if (newKind!=kind){				
				res.add(s.substring(ix, i));
				if (newKind==CBreaker){
					i++;
					newKind = getKind(s, i);
				}
				ix = i;
				kind = newKind;
			}
			i++;
		}
		res.add(s.substring(ix, i));
		return res;
	}

	private ArrayList<String> errArray() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(" ");
		return res;
	}

	public int getKind(String s, int i) {
		char c = s.charAt(i);
		if (isSpecialChar(c)){
			int j = i-1;
			while (j>=0 && isSpecialChar(s.charAt(j))) j--;
			int kindPre;
			if (j==-1) kindPre = CUndef; else kindPre = getKind(s, j);
			
			j = i+1;
			while (j<s.length() && isSpecialChar(s.charAt(j))) j++;
			int kindNext;
			if (j==s.length()) kindNext = CUndef; else kindNext = getKind(s, j);
		
			if (kindPre==kindNext) return kindPre;
			if (kindPre==CUndef && kindNext==CUndef) return CEnglish_Number;
			if (kindPre==CUndef) return kindNext;
			if (kindNext==CUndef) return kindPre;
			return getKindSpecialChar(c);
		}
		if (c==CCharForceBreaker) return CBreaker;
		/*if (c>=0x0660 && c<=0x0669) return CEnglish_Number;//arabic numbers
		if (c>=0x06F0 && c<=0x06F9) return CEnglish_Number;//arabic numbers*/
		if (Character.isDigit(c)) return CEnglish_Number; 
		if (c>0 && c<=127) {
			return CEnglish_Number;
		}
		
		return CFarsi;
	}

	/**
	 * 
	 * @param c
	 * @return A number greater or equal to 32 and lower than or equal to 127.
	 * The number indicates a unique code to classify special 
	 * characters independently. That is, to classify "dot" as dot not as an 
	 * English or Farsi letter.
	 */
	private int getKindSpecialChar(char c) {
		return c;
	}

	/**
	 * Special characters are weak in direction assignment. They can be
	 * either RTL or LTR. Add any new special character to the return command if
	 * it is needed.  
	 * @param c
	 * @return
	 */
	public static boolean isSpecialChar(char c) {		
		return c==' ' || c=='-' || c=='.' || c=='_' || c=='*' || c=='#' || c=='?'
			   || c == ':' || c == '،';
	}
	
	/**
	 * Only use this char at the middle of a phrase.
	 * @return A character to enforce phrase split.
	 * Only use this char at the middle of a phrase. 
	 */
	public static char getForceBreaker(){
		return CCharForceBreaker;
	}
}
