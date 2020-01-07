package org.kasabeh.androidprint.lib;

public class printerWordMng {
	private int maxChars;
	
	public printerWordMng(int maxChars) {
		this.maxChars = maxChars;
	}

	public boolean isFittable(String s){
		return (s.length()<=maxChars);
	}
	
	private String trim(String s){
		return s.substring(0, maxChars);
	}
	
	public String autoTrim(String s){
		if (isFittable(s)) return s;
		return trim(s);
	}	
	
	private String wrap(String inputStr){
		String nl = WoosimPrnMng.getSysLineSeparator();
		String res = "";
		
		String[] arrParts = inputStr.split(nl);
		for (String s : arrParts) {
			int ix = 0;
			for (int i=1;i<s.length();i++){
				if ((i % maxChars)==0){
					res = res + s.substring(ix, i) + nl;
					ix = i;
				}
			}
			res = res + s.substring(ix) + nl;			
		}
		res = res.substring(0, res.length()-1);
		return res;
	}
	
	public String autoWrap(String s){
		if (isFittable(s)) return s;
		return wrap(s);		
	}

	private String wordWrap(String inputStr) {
		String nl = WoosimPrnMng.getSysLineSeparator();
		String res = "";
		
		String[] arrParts = inputStr.split(nl);
		for (String parts : arrParts) {
			int len = 0;
			String[] arrWords = parts.split(" ");
			for (String s : arrWords) {
				len +=s.length();
				if (len<=maxChars){
					res = res + s + " ";
					len++;
				}else{
					if (res.length()>0){
						res = res.substring(0, res.length()-1);
						res = res + nl + autoTrim(s) + " ";
					}else{
						res = autoTrim(s) + " ";
					}
					len = s.length();
				}
			}
			res = res.substring(0, res.length()-1);
			res = res + nl;
		}
		res = res.substring(0, res.length()-1);
		return res;
	}
	
	public String autoWordWrap(String s){
		if (isFittable(s)) return s;
		return wordWrap(s);		
	}
	
	public String getHorizontalUnderline(){
		String s = "";
		for(int i = 1; i<=maxChars-2; i++) s+="_";
		return s;
	}
	
	public int getWidth(){
		return maxChars * IWoosimCanvasConst.CCharWidth;
	}
}
