package com.whz.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

/**
 * @Description 用于读取保存于File中的常用数据，文件存放于assets目录中，文件内容格式：[one,two]
 * Created by whz  on 2019-06-27
 */
public class DeviceUtil {

    /**
     * 唯一的设备ID：
     * GSM手机的 IMEI 和 CDMA手机的 MEID.
     * Return null if device ID is not available.
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 设备的软件版本号：
     * 例如：the IMEI/SV(software version) for GSM phones.
     * Return null if the software version is not available.
     */
    public static String getDeviceSoftwareVersion(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        return tm.getDeviceSoftwareVersion();
    }

    /**
     * 获取手机名称
     * @return
     */
    public static String getDeviceName(){
        return android.os.Build.MANUFACTURER;
    }
    /**
     * 获取应用名称
     *
     * @param context
     * @return
     */
    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return packageManager.getApplicationLabel(applicationInfo).toString();
    }
}
