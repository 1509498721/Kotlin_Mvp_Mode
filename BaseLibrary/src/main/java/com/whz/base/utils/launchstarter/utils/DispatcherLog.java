package com.whz.base.utils.launchstarter.utils;

import android.util.Log;

/**
 * Created by whz  on 2019-06-27
 */
public class DispatcherLog {

    private static boolean sDebug = true;

    public static void i(String msg) {
        if (!sDebug) {
            return;
        }
        Log.i("task",msg);
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void setDebug(boolean debug) {
        sDebug = debug;
    }

}
