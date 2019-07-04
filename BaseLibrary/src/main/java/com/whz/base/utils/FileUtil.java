package com.whz.base.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.*;


/**
 * @Description  文件操作
 * Created by whz  on 2019-06-27
 */
public class FileUtil {

    /**
     * 读取文件为byte[]
     *
     * @param filePath
     * @return
     */
    public static byte[] readFile(String filePath) {
        final File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        try {
            InputStream stream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            stream.read(buffer);
            stream.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存文件
     *
     * @param filePath
     * @param data
     */
    public static void saveFile(String filePath, byte[] data) {
        File targetFile = new File(filePath);
        FileOutputStream osw;
        try {
            if (!targetFile.exists()) {
                targetFile.createNewFile();
                osw = new FileOutputStream(targetFile);
                osw.write(data);
                osw.close();
            } else {
                osw = new FileOutputStream(targetFile, true);
                osw.write(data);
                osw.flush();
                osw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存对象为文件
     *
     * @param name   name.txt
     * @param object
     */
    public static boolean saveFile(String name, Object object) {
        if (null == object) {
            return false;
        }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File f = new File(SdCardUtil.PROJECT_FILE_PATH);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            fos = new FileOutputStream(SdCardUtil.PROJECT_FILE_PATH + name);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
            LogUtil.d("message", "object save succeed");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取对象文件
     *
     * @param path
     * @return
     */
    public static Object readObjectFile(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        File f = new File(path);
        if (!f.exists()) {
            return null;
        }
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            ois.close();
            fis.close();
            return object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件拷贝
     *
     * @param prefile
     * @param newfile
     */
    public static int copyFile(String prefile, String newfile) {
        try {
            InputStream fosfrom = new FileInputStream(prefile);
            OutputStream fosto = new FileOutputStream(newfile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;

        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * asset文件夹读取文件
     *
     * @param context
     * @param path
     * @return
     */
    public static String readTxtFromAsset(Context context, String path) {
        Resources resource = context.getResources();
        AssetManager am = resource.getAssets();
        InputStream is = null;
        String content = "";
        try {
            is = am.open(path);
            int length = is.available();
            byte[] buffer = new byte[length];
            is.read(buffer);
            content = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 从assets目录中复制整个文件夹内容
     *
     * @param context Context 使用CopyFiles类的Activity
     * @param oldPath String 原文件路径 如：/aa
     * @param newPath String 复制后路径 如：xx:/bb/cc
     */
    public static void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);// 获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {// 如果是目录
                File file = new File(newPath);
                file.mkdirs();// 如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {// 如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 如果捕捉到错误则通知UI线程
        }
    }

    /**
     * 从sd卡读文件
     *
     * @param path
     * @return
     */
    public static String readTxtFromSd(String path) {
        String content = "";
        InputStream stream = null;
        final File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {
            stream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            stream.read(buffer);
            content = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件路径
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public static void readVedioDuration(String file) {

    }
}
