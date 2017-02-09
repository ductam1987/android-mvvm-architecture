package com.android_mvvm.api;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by Tam Nguyen on 1/8/17.
 */

@GsonTypeAdapterFactory
public abstract class AutoValueGsonFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create(){
        return new AutoValueGson_AutoValueGsonFactory();
    }
}
