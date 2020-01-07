package org.kasabeh.androidprint.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageGallaryMng {

    public static Bitmap getBitmap(String filePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        return BitmapFactory.decodeFile(filePath, options);
    }
}
