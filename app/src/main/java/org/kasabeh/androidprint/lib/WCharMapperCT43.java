package org.kasabeh.androidprint.lib;

/**
 * Do the mapping based on the code table 43 or CT_ARABIC_FORMS_B
 * @author Saeed
 *
 */

public class WCharMapperCT43 extends WCharMapperCT42 {

	@Override
	protected int isolatedForm(char c) {
		if (c=='ا') return 144;
		if (c=='آ') return 141;
		if (c=='ب') return 146;
		if (c=='پ') return 148;
		if (c=='ت') return 150;
		if (c=='ث') return 152;
		if (c=='ج') return 154;
		if (c=='چ') return 156;
		if (c=='ح') return 158;
		if (c=='خ') return 160;
		if (c=='د') return 162;
		if (c=='ذ') return 163;
		if (c=='ر') return 164;
		if (c=='ز') return 165;
		if (c=='ژ') return 166;
		if (c=='س') return 167;
		if (c=='ش') return 169;
		if (c=='ص') return 171;
		if (c=='ض') return 173;
		if (c=='ط') return 175;
		if (c=='ظ') return 224;
		if (c=='ع') return 225;
		if (c=='غ') return 229;
		if (c=='ف') return 233;
		if (c=='ق') return 235;
		if (c=='ک') return 237;
		if (c=='ك') return 237;
		if (c=='گ') return 239;
		if (c=='ل') return 241;
		if (c=='م') return 244;
		if (c=='ن') return 246;
		if (c=='و') return 248;
		if (c=='ه') return 249;
		if (c=='ی') return 253;
		if (c=='ي') return 253;
		return otherChars(c);
	}

	@Override
	protected int initialForm(char c) {
		if (c=='ا') return 144;
		if (c=='آ') return 141;
		if (c=='ب') return 147;
		if (c=='پ') return 149;
		if (c=='ت') return 151;
		if (c=='ث') return 153;
		if (c=='ج') return 155;
		if (c=='چ') return 157;
		if (c=='ح') return 159;
		if (c=='خ') return 161;
		if (c=='د') return 162;
		if (c=='ذ') return 163;
		if (c=='ر') return 164;
		if (c=='ز') return 165;
		if (c=='ژ') return 166;
		if (c=='س') return 168;
		if (c=='ش') return 170;
		if (c=='ص') return 172;
		if (c=='ض') return 174;
		if (c=='ط') return 175;
		if (c=='ظ') return 224;
		if (c=='ع') return 228;
		if (c=='غ') return 232;
		if (c=='ف') return 234;
		if (c=='ق') return 236;
		if (c=='ک') return 238;
		if (c=='ك') return 238;
		if (c=='گ') return 240;
		if (c=='ل') return 243;
		if (c=='م') return 245;
		if (c=='ن') return 247;
		if (c=='و') return 248;
		if (c=='ه') return 251;
		if (c=='ی') return 254;
		if (c=='ي') return 254;
		return otherChars(c);		
	}
	
	@Override
	protected int otherChars(char c) {
		//TODO more characters such as Arabic question mark (?) may be added here.		
		if (c=='،') return 138;
		return 139;
	}

	@Override
	protected int medialForm(char c) {
		if (c=='ا') return 145;
		if (c=='آ') return 145;
		if (c=='ب') return 147;
		if (c=='پ') return 149;
		if (c=='ت') return 151;
		if (c=='ث') return 153;
		if (c=='ج') return 155;
		if (c=='چ') return 157;
		if (c=='ح') return 159;
		if (c=='خ') return 161;
		if (c=='د') return 162;
		if (c=='ذ') return 163;
		if (c=='ر') return 164;
		if (c=='ز') return 165;
		if (c=='ژ') return 166;
		if (c=='س') return 168;
		if (c=='ش') return 170;
		if (c=='ص') return 172;
		if (c=='ض') return 174;
		if (c=='ط') return 175;
		if (c=='ظ') return 224;
		if (c=='ع') return 227;
		if (c=='غ') return 231;
		if (c=='ف') return 234;
		if (c=='ق') return 236;
		if (c=='ک') return 238;
		if (c=='ك') return 238;
		if (c=='گ') return 240;
		if (c=='ل') return 243;
		if (c=='م') return 245;
		if (c=='ن') return 247;
		if (c=='و') return 248;
		if (c=='ه') return 250;
		if (c=='ی') return 254;
		if (c=='ي') return 254;
		return otherChars(c);
	}
	
	@Override
	protected int finalForm(char c) {
		if (c=='ا') return 145;
		if (c=='آ') return 145;
		if (c=='ب') return 146;
		if (c=='پ') return 148;
		if (c=='ت') return 150;
		if (c=='ث') return 152;
		if (c=='ج') return 154;
		if (c=='چ') return 156;
		if (c=='ح') return 158;
		if (c=='خ') return 160;
		if (c=='د') return 162;
		if (c=='ذ') return 163;
		if (c=='ر') return 164;
		if (c=='ز') return 165;
		if (c=='ژ') return 166;
		if (c=='س') return 167;
		if (c=='ش') return 169;
		if (c=='ص') return 171;
		if (c=='ض') return 173;
		if (c=='ط') return 175;
		if (c=='ظ') return 224;
		if (c=='ع') return 226;
		if (c=='غ') return 230;
		if (c=='ف') return 233;
		if (c=='ق') return 235;
		if (c=='ک') return 237;
		if (c=='ك') return 237;
		if (c=='گ') return 239;
		if (c=='ل') return 241;
		if (c=='م') return 244;
		if (c=='ن') return 246;
		if (c=='و') return 248;
		if (c=='ه') return 249;
		if (c=='ی') return 252;
		if (c=='ي') return 252;
		return otherChars(c);
	}	
}
