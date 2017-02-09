package com.android_mvvm.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android_mvvm.db.modelDB.Favorites;
import com.android_mvvm.model.Results;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Tam Nguyen on 1/7/17.
 */

public class Utils {

    private static final String TAG = Utils.class.getName();

    public static void convertObjectToRealm(Context context, Results results, Favorites favorite, Bitmap bitmap){
        favorite.id = results.id();
        favorite.title = results.title();
        favorite.overview  = results.overview();
        favorite.poster = (saveFile(String.valueOf(favorite.id)+".png",bitmap,context));
        Log.d(TAG, "Image favorite.poster_path = " + favorite.poster);
    }

    private static String saveFile(String imageName, Bitmap bitmap, Context context){
        if(bitmap == null){
            return null;
        }
        final File myImageFile = new File(context.getFilesDir(), imageName); // Create image file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myImageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "Image file local = " + myImageFile.getAbsolutePath());
        return myImageFile.getAbsolutePath();
    }

}
