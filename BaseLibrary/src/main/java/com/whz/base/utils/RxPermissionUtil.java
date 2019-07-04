package com.whz.base.utils;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * PackageName: com.example.ken.librarypack.Util
 * Author : jiaqi Ye
 * Date : 2018/6/29
 * Time : 11:54
 */

public class RxPermissionUtil {
    private static RxPermissionUtil instance = null;
    private static RxPermissions rxPermissions = null;

    private RxPermissionUtil(){}

    public static RxPermissionUtil init() {
        if(instance == null){
            synchronized (RxPermissionUtil.class){
                if(instance == null){
                    instance = new RxPermissionUtil();
                }
            }
        }
        return instance;
    }

    public static RxPermissions with(Activity activity){
        rxPermissions = new RxPermissions((FragmentActivity) activity);
        return rxPermissions;
    }




}
