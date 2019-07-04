package com.whz.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by whz  on 2019-06-27
 */
public class SharedPreUtil {

    private  static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public static void init(Context context , String saveName){
        sp = context.getSharedPreferences(saveName, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static void putString(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }

    public static void putBoolean(String key, Boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }

    public static void putFloat(String key, float value){
        editor.putFloat(key,value);
        editor.commit();
    }

    public static void putLong(String key, long value){
        editor.putLong(key,value);
        editor.commit();
    }

    public static void putInt(String key, int value){
        editor.putInt(key,value);
        editor.commit();
    }

    public static float getFloat(String key){
        return sp.getFloat(key,0);
    }

    public static long getLong(String key){
        return sp.getLong(key,0);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue){
        return sp.getBoolean(key,defaultValue);
    }


    public static Boolean getBoolean(String key){
        return sp.getBoolean(key,false);
    }

    public static String getString(String key, String defaultValue){
        return sp.getString(key,defaultValue);
    }

    public static String getString(String key){
        return sp.getString(key,"");
    }


    public static void removeValue(String key) {
        editor.remove(key);
        editor.commit();
    }

    public static void clear(){
        editor.clear();
        editor.commit();
    }

}
