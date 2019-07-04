package com.whz.base.utils;


import android.content.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @Description 用于读取保存于File中的常用数据，文件存放于assets目录中，文件内容格式：[one,two]
 * Created by whz  on 2019-06-27
 */
public class FileDataUtil {

    private String FILE_AREA ;
    private Context context;

    private HashMap<String, List<String>> map;


    private static FileDataUtil instance;

    private FileDataUtil(Context context, String fileName) {
        this.context = context;
        this.FILE_AREA = fileName+".txt";
    }

    /**
     * @param context
     */
    public static FileDataUtil getInstance(Context context, String fileName) {
        if (instance == null) {
            instance = new FileDataUtil(context,fileName);
        }
        return instance;
    }

    /**
     * 通过与文件中的字段匹配，返回相应的数据
     * @param index 文件内容：[one,two]  index:one
     * @return  two
     */
    public List<String> getDataByIndex(String index) {
//        缓存判断
        if(null == map){
            map = new HashMap<String, List<String>>();
        }
        if(map.get(index) != null){
           return map.get(index);
        }
        List<String> list = new ArrayList<String>();
        InputStream in = null;
        BufferedReader bufferReader = null;
        String str = "";
        String[] supstr;
        try {
            in = context.getAssets().open(this.FILE_AREA);
            bufferReader = new BufferedReader(
                    new InputStreamReader(in, "UTF-8"));
            while ((str = bufferReader.readLine()) != null) {
                supstr = str.split(";");
                if(supstr[0].equals(index)){
                    list.add(supstr[1]);
                }else if(supstr[1].equals(index)){
                    list.add(supstr[0]);
                }
            }
//            缓存
            map.put(index,list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeBuffer(bufferReader);
            closeInputStream(in);
        }
        return list;
    }
    /**
     * 关闭Reader
     * @param reader
     */
    public void closeBuffer(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭InputStream
     *
     * @param in
     */
    public void closeInputStream(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

