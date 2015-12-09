package com.example.caelum.agendaalunos.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by android5843 on 09/12/15.
 */
public final class ImageUtils {

    public static Bitmap createBitmapImage(String imagePath, int height) {
        Bitmap imageFile = BitmapFactory.decodeFile(imagePath);
        if(imageFile == null) {
            return null;
        }

        return Bitmap.createScaledBitmap(imageFile, imageFile.getWidth(), height, true);
    }

}
