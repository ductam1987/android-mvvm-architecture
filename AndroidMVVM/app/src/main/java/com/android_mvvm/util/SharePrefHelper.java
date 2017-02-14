package com.android_mvvm.util;

import android.content.SharedPreferences;
import android.renderscript.Float3;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by fahc03-179 on 2/13/17.
 */

@Singleton
public class SharePrefHelper {

    public static String PREF_KEY_ACCESS_TOKE = "access-token";

    private SharedPreferences mSharedPreferences;

    @Inject
    public SharePrefHelper(SharedPreferences sharedPreferences){
        mSharedPreferences = sharedPreferences;
    }

    public void put(String key, String value){
        mSharedPreferences.edit().putString(key,value).apply();
    }

    public void put(String key, int value){
        mSharedPreferences.edit().putInt(key,value).apply();
    }

    public void put(String key, float value){
        mSharedPreferences.edit().putFloat(key,value).apply();
    }

    public void put(String key, boolean value){
        mSharedPreferences.edit().putBoolean(key,value).apply();
    }

    public String get(String key, String defaultValue){
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue){
        return mSharedPreferences.getInt(key,defaultValue);
    }

    public Float get(String key, float defaultValue){
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue){
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSaveData(String key){
        mSharedPreferences.edit().remove(key).apply();
    }
}
