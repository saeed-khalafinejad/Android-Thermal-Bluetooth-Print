package org.kasabeh.androidprint.lib;

public class WCharMapperRongtaDef extends WCharMapperCT41{

    @Override
    protected int isolatedForm(char c) {
        if (c=='ا') return 0x90;
        if (c=='آ') return 0x8D;
        if (c=='ب') return 0x92;
        if (c=='پ') return 0x94;
        if (c=='ت') return 0x96;
        if (c=='ث') return 0x98;
        if (c=='ج') return 0x9A;
        if (c=='چ') return 0x9C;
        if (c=='ح') return 0x9E;
        if (c=='خ') return 0xA0;
        if (c=='د') return 0xA2;
        if (c=='ذ') return 0xA3;
        if (c=='ر') return 0xA4;
        if (c=='ز') return 0xA5;
        if (c=='ژ') return 0xA6;
        if (c=='س') return 0xA7;
        if (c=='ش') return 0xA9;
        if (c=='ص') return 0xAB;
        if (c=='ض') return 0xAD;
        if (c=='ط') return 0xAF;
        if (c=='ظ') return 0xE0;
        if (c=='ع') return 0xE1;
        if (c=='غ') return 0xE5;
        if (c=='ف') return 0xE9;
        if (c=='ق') return 0xEB;
        if (c=='ک') return 0xED;
        if (c=='ك') return 0xED;
        if (c=='گ') return 0xEF;
        if (c=='ل') return 0xF1;
        if (c=='م') return 0xF4;
        if (c=='ن') return 0xF6;
        if (c=='و') return 0xF8;
        if (c=='ه') return 0xF9;
        if (c=='ی') return 0xFD;
        if (c=='ي') return 0xFD;
        return otherChars(c);
    }

    @Override
    protected int initialForm(char c) {
        if (c=='ا') return 0x90;
        if (c=='آ') return 0x8D;
        if (c=='ب') return 0x93;
        if (c=='پ') return 0x95;
        if (c=='ت') return 0x97;
        if (c=='ث') return 0x99;
        if (c=='ج') return 0x9B;
        if (c=='چ') return 0x9D;
        if (c=='ح') return 0x9F;
        if (c=='خ') return 0xA1;
        if (c=='د') return 0xA2;
        if (c=='ذ') return 0xA3;
        if (c=='ر') return 0xA4;
        if (c=='ز') return 0xA5;
        if (c=='ژ') return 0xA6;
        if (c=='س') return 0xA8;
        if (c=='ش') return 0xAA;
        if (c=='ص') return 0xAC;
        if (c=='ض') return 0xAE;
        if (c=='ط') return 0xAF;
        if (c=='ظ') return 0xE0;
        if (c=='ع') return 0xE4;
        if (c=='غ') return 0xE8;
        if (c=='ف') return 0xEA;
        if (c=='ق') return 0xEC;
        if (c=='ک') return 0xEE;
        if (c=='ك') return 0xEE;
        if (c=='گ') return 0xF0;
        if (c=='ل') return 0xF3;
        if (c=='م') return 0xF5;
        if (c=='ن') return 0xF7;
        if (c=='و') return 0xF8;
        if (c=='ه') return 0xFB;
        if (c=='ی') return 0xFE;
        if (c=='ي') return 0xFE;
        return otherChars(c);
    }

    @Override
    protected int otherChars(char c) {
        //TODO more characters such as Arabic question mark (?) may be added here.
        if (c=='،') return 0x8A;
        return 0xDC;
    }

    @Override
    protected int medialForm(char c) {
        if (c=='ا') return 0x91;
        if (c=='آ') return 0x91;
        if (c=='ب') return 0x93;
        if (c=='پ') return 0x95;
        if (c=='ت') return 0x97;
        if (c=='ث') return 0x99;
        if (c=='ج') return 0x9B;
        if (c=='چ') return 0x9D;
        if (c=='ح') return 0x9F;
        if (c=='خ') return 0xA1;
        if (c=='د') return 0xA2;
        if (c=='ذ') return 0xA3;
        if (c=='ر') return 0xA4;
        if (c=='ز') return 0xA5;
        if (c=='ژ') return 0xA6;
        if (c=='س') return 0xA8;
        if (c=='ش') return 0xAA;
        if (c=='ص') return 0xAC;
        if (c=='ض') return 0xAE;
        if (c=='ط') return 0xAF;
        if (c=='ظ') return 0xE0;
        if (c=='ع') return 0xE3;
        if (c=='غ') return 0xE7;
        if (c=='ف') return 0xEA;
        if (c=='ق') return 0xEC;
        if (c=='ک') return 0xEE;
        if (c=='ك') return 0xEE;
        if (c=='گ') return 0xF0;
        if (c=='ل') return 0xF3;
        if (c=='م') return 0xF5;
        if (c=='ن') return 0xF7;
        if (c=='و') return 0xF8;
        if (c=='ه') return 0xFA;
        if (c=='ی') return 0xFE;
        if (c=='ي') return 0xFE;
        return otherChars(c);
    }

    @Override
    protected int finalForm(char c) {
        if (c=='ا') return 0x91;
        if (c=='آ') return 0x91;
        if (c=='ب') return 0x92;
        if (c=='پ') return 0x94;
        if (c=='ت') return 0x96;
        if (c=='ث') return 0x98;
        if (c=='ج') return 0x9A;
        if (c=='چ') return 0x9C;
        if (c=='ح') return 0x9E;
        if (c=='خ') return 0xA0;
        if (c=='د') return 0xA2;
        if (c=='ذ') return 0xA3;
        if (c=='ر') return 0xA4;
        if (c=='ز') return 0xA5;
        if (c=='ژ') return 0xA6;
        if (c=='س') return 0xA7;
        if (c=='ش') return 0xA9;
        if (c=='ص') return 0xAB;
        if (c=='ض') return 0xAD;
        if (c=='ط') return 0xAF;
        if (c=='ظ') return 0xE0;
        if (c=='ع') return 0xE2;
        if (c=='غ') return 0xE6;
        if (c=='ف') return 0xE9;
        if (c=='ق') return 0xEB;
        if (c=='ک') return 0xED;
        if (c=='ك') return 0xED;
        if (c=='گ') return 0xEF;
        if (c=='ل') return 0xF1;
        if (c=='م') return 0xF4;
        if (c=='ن') return 0xF6;
        if (c=='و') return 0xF8;
        if (c=='ه') return 0xF9;
        if (c=='ی') return 0xFC;
        if (c=='ي') return 0xFC;
        return otherChars(c);
    }
}
