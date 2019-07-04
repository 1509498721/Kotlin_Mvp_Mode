package com.whz.base.utils

import android.content.res.Resources
import android.util.DisplayMetrics;

/**
 *Created by whz  on 2019-06-27
 */
object SizeUtil {

    fun getDisplayMetrics(): DisplayMetrics {
        return Resources.getSystem().displayMetrics
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    fun getScreenWidth(): Int {
        return getDisplayMetrics().widthPixels
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    fun getScreenHeight(): Int {
        return getDisplayMetrics().heightPixels
    }

    /**
     * px转dp
     *
     * @param pxValue
     * @return
     */
    fun px2dp(pxValue: Float): Int {
        val scale = getDisplayMetrics().density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * dp转px
     *
     * @param dpValue
     * @return
     */
    fun dp2px(dpValue: Float): Int {
        val scale = getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px转sp
     *
     * @param pxValue
     * @return
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = getDisplayMetrics().scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * sp转px
     *
     * @param spValue
     * @return
     */
    fun sp2px(spValue: Float): Int {
        val fontScale = getDisplayMetrics().scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

}