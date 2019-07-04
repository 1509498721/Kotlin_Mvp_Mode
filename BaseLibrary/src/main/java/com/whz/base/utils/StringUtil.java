package com.whz.base.utils;

import android.text.TextUtils;

import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 * @Description: 字符类型转换工具
 * Created by whz  on 2019-06-27
 */

public class StringUtil {

    /**
     * 字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }


    public static String toString(Object obj) {
        if (obj == null)
            return "";
        else
            return obj.toString();
    }

    public static int toInt(Object obj) {
        if (obj == null || "".equals(obj) || "null".equals(obj)) {
            return -1;
        } else {
            try {
                return Integer.parseInt(obj.toString());
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    public static short toShort(Object obj) {
        if (obj == null || "".equals(obj) || "null".equals(obj)) {
            return -1;
        } else {
            return Short.parseShort(obj.toString());
        }
    }

    public static int toCount(Object obj) {
        if (obj == null || "".equals(obj) || "null".equals(obj)) {
            return 0;
        } else {
            return Integer.parseInt(obj.toString());
        }
    }

    public static float toFloat(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        } else {
            return Float.parseFloat(obj.toString());
        }
    }

    public static double toDouble(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        } else {
            try {
                return Double.parseDouble(obj.toString());
            } catch (NumberFormatException e) {
                return -1L;
            }
        }
    }


    /**
     * 保留两位小数
     * @param num
     * @return
     */
    public static String double2Decimal(double num){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setGroupingSize(0);
        df.setRoundingMode(RoundingMode.FLOOR);
        String style = "#,##0.00";// 定义要显示的数字的格式
        df.applyPattern(style);
        return df.format(num);
    }


    /**
     * 保留两位小数
     * @param num
     * @return
     */
    public static String float2Decimal(float num){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setGroupingSize(0);
        df.setRoundingMode(RoundingMode.FLOOR);
        String style = "#,##0.00";// 定义要显示的数字的格式
        df.applyPattern(style);
        return df.format(num);
    }

    /**
     * 将对象转换成Long型空对象默认装换成0
     *
     * @param obj
     * @return
     */
    public static Long toLong(Object obj) {
        if (obj == null || "".equals(obj)) {
            return -1L;
        } else {
            try {
                return Long.parseLong(obj.toString());
            } catch (NumberFormatException e) {
                return -1L;
            }

        }
    }

    /**
     * 将对象转换成boolean类型,默认为false
     *
     * @param obj
     * @return
     */
    public static Boolean toBoolean(Object obj) {
        if (obj == null || "".equals(obj)) {
            return false;
        }
        return Boolean.valueOf(obj.toString());
    }

    public static boolean checkStr(String str) {
        boolean bool = true;
        if (str == null || "".equals(str.trim()))
            bool = false;
        return bool;
    }

    public static String buildFirstChar(String str) {
        if (str == null)
            return null;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static double double2point(double ff) {
        int j = (int) Math.round(ff * 10000);
        double k = (double) j / 100.00;

        return k;
    }

    public String snumberFormat(double unm) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(unm);
    }

    public static String delSpaceString(String d) {
        String ret = "";
        if (d != null) {
            ret = d.trim();
        }
        return ret;
    }

    /**
     * 数据定长输出
     *
     * @param pattern 长度及其格式（例如：定长�?0位，不足则前面补零，那么pattern�?0000000000"�?
     * @param number
     * @return
     */
    public static String getDecimalFormat(String pattern, String number) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(pattern);
        int num = Integer.parseInt(toInt(number) + "");
        if ((num + "").length() > pattern.length()) {
            String newNumber = (num + "").substring(0, pattern.length() - 1);
            num = Integer.parseInt(newNumber);
        }
        return myformat.format(num);
    }

    public static String formatDecimal(String pattern, int number) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(pattern);
        if ((number + "").length() > pattern.length()) {
            String newNumber = (number + "").substring(0, pattern.length() - 1);
            number = Integer.parseInt(newNumber);
        }
        return myformat.format(number);
    }

    public static String bytesToHexString(byte[] src, int length) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || length <= 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static float max(float... values) {
        float max = 0;
        for (float item : values) {
            if (max == 0) {
                max = item;
            } else {
                max = Math.max(max, item);
            }
        }
        return max;
    }

    public static float min(float... values) {
        float min = 0;
        for (float item : values) {
            if (min == 0) {
                min = item;
            } else {
                if (item == 0) {
                    continue;
                }
                min = Math.min(min, item);
            }
        }
        return min;
    }

    public static int level(float value, float level[]) {
        for (int i = 0; i < level.length; i++) {
            if (value < level[i]) {
                return i;
            }
        }
        return level.length;
    }


    /**
     * @param @param  number
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: topercent
     * @Description: 将double类型的数据改为百分数，默认显示两位小数
     */
    public static String toPercent(double number) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);
        return percentFormat.format(number);

    }

    public static String toNumberFormat(double number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        return numberFormat.format(number);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /**
     * 判断是否是字母+数字
     *
     * @param number
     * @return
     */
    public static boolean isDigitalAndAlphabet(String number) {
        Pattern p1 = Pattern.compile("[0-9]*$");
        Pattern p2 = Pattern.compile("^[A-Za-z]+$");
        if (p1.matcher(number).matches() || p2.matcher(number).matches()) {
            return false;
        }
        return true;
    }

    /**
     * @param @param  phone
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: phone2Unknown
     * @Description: 将手机号中间4位变为****
     */
    public static String phone2Unknown(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4, phone.length());
    }

    /**
     * 将数据的后len位置為****
     *
     * @param data
     * @param len
     * @return
     */
    public static String parseToLastUnknown(String data, int len) {
        int count = data.length();
        StringBuffer sb = new StringBuffer();
        if (count > len) {
            for (int i = 0; i < len; i++) {
                sb.append("*");
            }
            return data.substring(0, count - len) + sb.toString();
        } else {
            return data;
        }

    }

    /**
     * 保留前len位
     *
     * @param data
     * @param len
     * @return
     */
    public static String remainFirstWords(String data, int len) {
        int count = data.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append("*");
        }
        if (count < len) {
            return data;
        } else {
            return sb.toString() + data.substring(len, count);
        }
    }

//    /**
//     * @param @param  object
//     * @param @return 设定文件
//     * @return String 返回类型
//     * @throws
//     * @Title: to2Decimal
//     * @Description: 保留两位小数
//     */
//    public static String to2Decimal(double object) {
//        DecimalFormat df = new DecimalFormat();
//        String style = "#,##0.00";// 定义要显示的数字的格式
//        df.applyPattern(style);
//        return df.format(object);
//    }

    /**
     * @param @param  object
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: to1Decimal
     * @Description: 保留一位小数，四舍五入
     */
    public static String to1Decimal(double data) {
        DecimalFormat df = new DecimalFormat();
        String style = "#.0";// 定义要显示的数字的格式
        df.applyPattern(style);
        return df.format(data);
    }

    /**
     * @param @param  object
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: to2Decimal
     * @Description: 保留两位小数，不进行四舍五入
     */
    public static String to2Decimal(double data) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setGroupingSize(0);
        df.setRoundingMode(RoundingMode.FLOOR);
        String style = "#,##0.00";// 定义要显示的数字的格式
        df.applyPattern(style);
        return df.format(data);
    }

    /**
     * @param @param  object
     * @param @return 设定文件
     * @return double 返回类型
     * @throws
     * @Title: to2Double
     * @Description: 四舍五入，保留两位小数，返回double
     */
    public static double to2Double(double data) {
        DecimalFormat df = new DecimalFormat();
        String style = "#.00";// 定义要显示的数字的格式
        df.applyPattern(style);
        return StringUtil.toDouble(df.format(data));
    }

    public static String toFormatLong(long data) {
        DecimalFormat df = new DecimalFormat();
        String style = "#,###";// 定义要显示的数字的格式
        df.applyPattern(style);
        return df.format(data);
    }

    public static String toHundred(long data) {
        int remainder = (int) (data % 100 / 10);
        if (remainder >= 5) {
            return String.valueOf(((data / 100) + 1) * 100);
        } else {
            return String.valueOf(data / 100 * 100);
        }

    }

    /**
     * @param @param  value
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     * @Title: getStringLength
     * @Description: 获取字符的长度（中文算2，英文算1）
     */
    public static int getStringLength(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < value.length(); i++) {
            // 获取一个字符
            String temp = value.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 2;
            } else {
                // 其他字符长度为0.5
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换数字为中文
     *
     * @param s
     * @return
     */
    public static String convertDecimal2Text(String s) {
        int i = toInt(s);
        String[] strs = {"", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
        if (i > 0 && i < strs.length) {
            return strs[i];
        }
        return strs[0];
    }

    /**
     * 转换数字y月份转为英文
     *
     * @param s
     * @return
     */
    public static String convertMonth(String s) {
        int i = toInt(s);
        String[] strs = {"", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        if (i > 0 && i < strs.length) {
            return strs[i];
        }
        return strs[0];
    }


    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 检测邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    // 将数字转换成字母
    public static String numToLetter(String input) {
        String Letter = "";
        for (byte b : input.getBytes()) {
            Letter = (char) (b + 48)+"";
        }
        return Letter;
    }
}
