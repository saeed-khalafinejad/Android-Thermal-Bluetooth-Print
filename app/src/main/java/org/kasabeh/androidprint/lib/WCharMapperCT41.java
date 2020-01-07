package org.kasabeh.androidprint.lib;

/**
 * Map characters base on the code table 41 that is received on 1395/09/17 (It is Iranian date format).
 * @author Saeed
 *
 */
public class WCharMapperCT41 extends WCharMapperCT42{

	@Override
	protected int isolatedForm(char c) {
		if (c=='ا') return 129;
		if (c=='آ') return 128;
		if (c=='ب') return 130;
		if (c=='پ') return 131;
		if (c=='ت') return 132;
		if (c=='ث') return 133;
		if (c=='ج') return 134;
		if (c=='چ') return 135;
		if (c=='ح') return 136;
		if (c=='خ') return 137;
		if (c=='د') return 138;
		if (c=='ذ') return 139;
		if (c=='ر') return 140;
		if (c=='ز') return 141;
		if (c=='ژ') return 142;
		if (c=='س') return 143;
		if (c=='ش') return 144;
		if (c=='ص') return 145;
		if (c=='ض') return 146;
		if (c=='ط') return 147;
		if (c=='ظ') return 148;
		if (c=='ع') return 149;
		if (c=='غ') return 150;
		if (c=='ف') return 151;
		if (c=='ق') return 152;
		if (c=='ک') return 153;
		if (c=='ك') return 153;
		if (c=='گ') return 154;
		if (c=='ل') return 155;
		if (c=='م') return 156;
		if (c=='ن') return 157;
		if (c=='و') return 158;
		if (c=='ه') return 159;
		if (c=='ی') return 160;
		if (c=='ي') return 160;
		return otherChars(c);
	}

	@Override
	protected int initialForm(char c) {
		if (c=='ا') return 128;
		if (c=='آ') return 129;
		if (c=='ب') return 161;
		if (c=='پ') return 162;
		if (c=='ت') return 163;
		if (c=='ث') return 164;
		if (c=='ج') return 165;
		if (c=='چ') return 166;
		if (c=='ح') return 167;
		if (c=='خ') return 168;
		if (c=='د') return 138;
		if (c=='ذ') return 139;
		if (c=='ر') return 140;
		if (c=='ز') return 141;
		if (c=='ژ') return 142;
		if (c=='س') return 169;
		if (c=='ش') return 170;
		if (c=='ص') return 171;
		if (c=='ض') return 172;
		if (c=='ط') return 147;
		if (c=='ظ') return 148;
		if (c=='ع') return 173;
		if (c=='غ') return 174;
		if (c=='ف') return 175;
		if (c=='ق') return 176;
		if (c=='ک') return 177;
		if (c=='ك') return 177;
		if (c=='گ') return 178;
		if (c=='ل') return 179;
		if (c=='م') return 180;
		if (c=='ن') return 181;
		if (c=='و') return 158;
		if (c=='ه') return 182;
		if (c=='ی') return 183;
		if (c=='ي') return 183;
		return otherChars(c);		
	}
	
	@Override
	protected int otherChars(char c) {
		//TODO more characters such as Arabic question mark (?) may be added here.		
		if (c=='،') return 221;
		return 244;
	}

	@Override
	protected int medialForm(char c) {
		if (c=='ا') return 203;
		if (c=='آ') return 203;
		if (c=='ب') return 184;
		if (c=='پ') return 185;
		if (c=='ت') return 186;
		if (c=='ث') return 187;
		if (c=='ج') return 188;
		if (c=='چ') return 189;
		if (c=='ح') return 190;
		if (c=='خ') return 191;
		if (c=='د') return 204;
		if (c=='ذ') return 205;
		if (c=='ر') return 206;
		if (c=='ز') return 207;
		if (c=='ژ') return 208;
		if (c=='س') return 169;
		if (c=='ش') return 170;
		if (c=='ص') return 171;
		if (c=='ض') return 172;
		if (c=='ط') return 147;
		if (c=='ظ') return 148;
		if (c=='ع') return 192;
		if (c=='غ') return 193;
		if (c=='ف') return 194;
		if (c=='ق') return 195;
		if (c=='ک') return 196;
		if (c=='ك') return 196;
		if (c=='گ') return 197;
		if (c=='ل') return 198;
		if (c=='م') return 199;
		if (c=='ن') return 200;
		if (c=='و') return 214;
		if (c=='ه') return 201;
		if (c=='ی') return 202;
		if (c=='ي') return 202;
		return otherChars(c);
	}
	
	@Override
	protected int finalForm(char c) {
		if (c=='ا') return 203;
		if (c=='آ') return 203;
		if (c=='ب') return 130;
		if (c=='پ') return 131;
		if (c=='ت') return 132;
		if (c=='ث') return 133;
		if (c=='ج') return 134;
		if (c=='چ') return 135;
		if (c=='ح') return 136;
		if (c=='خ') return 137;
		if (c=='د') return 204;
		if (c=='ذ') return 205;
		if (c=='ر') return 206;
		if (c=='ز') return 207;
		if (c=='ژ') return 208;
		if (c=='س') return 143;
		if (c=='ش') return 144;
		if (c=='ص') return 145;
		if (c=='ض') return 146;
		if (c=='ط') return 147;
		if (c=='ظ') return 148;
		if (c=='ع') return 209;
		if (c=='غ') return 210;
		if (c=='ف') return 151;
		if (c=='ق') return 211;
		if (c=='ک') return 153;
		if (c=='ك') return 153;
		if (c=='گ') return 154;
		if (c=='ل') return 212;
		if (c=='م') return 156;
		if (c=='ن') return 213;
		if (c=='و') return 214;
		if (c=='ه') return 215;
		if (c=='ی') return 216;
		if (c=='ي') return 216;
		return otherChars(c);
	}
}
