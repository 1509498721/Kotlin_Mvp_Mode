package com.whz.base.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by whz  on 2019-06-27
 */
public class SdCardUtil {

    public static String catch_path = ""; // 应用的cache目录用于存放缓存
    public static final String PROJECT_FILE_PATH = Environment
            .getExternalStorageDirectory().getPath() + "/" + "qqj" + "/"; // 项目路径
    public static final String DEFAULT_PHOTO_PATH = PROJECT_FILE_PATH +"pics/";
    public static final String DEFAULT_RECORD_PATH =PROJECT_FILE_PATH + "record/";
    public static String TEMP = "file:///" + PROJECT_FILE_PATH + "camera.jpg";

    /**
     * 判断是否有sd卡
     */
    public static boolean checkSdState() {
        String status = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(status);
    }

    /**
     * 获取Sd卡路径
     */
    public static String getSd() {
        if (!checkSdState()) {
            return "";
        }
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取相册路径
     */
    public static String getDCIM() {
        if (!checkSdState()) {
            return "";
        }
        String path = getSd() + "/dcim/";
        if (new File(path).exists()) {
            return path;
        }
        path = getSd() + "/DCIM/";
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return "";
            }
        }
        return path;
    }

    /**
     * 获取DCIM目录下的Camera目录
     */
    public static String getCamera() {
        if (!checkSdState()) {
            return "";
        }
        String path = getDCIM() + "/Camera/";
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return "";
            }
        }
        return path;
    }

    /**
     * 获取app缓存目录
     */
    public static String getCacheDir(Context context) {
        if (!checkSdState()) {
            return "";
        }
        return getSd() + "/Android/object/" + context.getPackageName() + "/cache/";
    }

    /**
     * 获取app目录
     */
    public static String getPrjFileDir(Context context) {
        if (!checkSdState()) {
            return "";
        }
        String path = getSd() + File.separator + DeviceUtil.getApplicationName(context) + File.separator;
        File projectDir = new File(path);
        if (!projectDir.exists()) {
            if (!projectDir.mkdirs()) {
                return "";
            }
        }
        return path;
    }

    /**
     * 相册目录下的图片路径
     */
    public static String getSysImgPath() {
        if (!checkSdState()) {
            return "";
        }
        return getCamera() + "IMG_" + System.currentTimeMillis() + ".jpg";
    }

    /**
     * app目录下的图片路径
     */
    public static String getAppImgPath(Context context) {
        String prj = getPrjFileDir(context);
        if ("".equals(prj)) {
            return "";
        }
        return prj + "IMG_" + System.currentTimeMillis() + ".jpg";
    }

    /**
     * 获取缓存图片路径
     *
     * @param context
     * @return
     */
    public static String getCacheImage(Context context) {
        return getCacheDir(context) + "IMG_" + System.currentTimeMillis() + ".jpg";
    }

    /**
     * 获取拓展存储Cache的绝对路径
     *
     * @param context
     */
    public static String getExternalCacheDir(Context context) {
        if (!SdCardUtil.checkSdState())
            return null;
        StringBuilder sb = new StringBuilder();
        File file = context.getExternalCacheDir();
        if (file != null) {
            sb.append(file.getAbsolutePath()).append(File.separator);
        } else {
            sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Android/object/").append(context
                    .getPackageName())
                    .append("/cache/").append(File.separator).toString();
        }
        return sb.toString();
    }

    public static String getCacheTempImage(Context context) {
        return getExternalCacheDir(context) + System.currentTimeMillis() + ".jpg";

    }
    /**
     * 初始化文件目录
     */
    public static void initFileDir(Context context) {
        File projectDir = new File(PROJECT_FILE_PATH);
        if (!projectDir.exists()) {
            projectDir.mkdirs();
        }
        File fileDir = new File(DEFAULT_PHOTO_PATH);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File recordDir = new File(DEFAULT_RECORD_PATH);
        if(!recordDir.exists()){
            recordDir.mkdirs();
        }
        catch_path = Environment.getExternalStorageDirectory().getPath()
                + "/Android/object/" + context.getPackageName() + "/cache/";
    }
}
