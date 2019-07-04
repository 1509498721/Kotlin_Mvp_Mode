package com.whz.base.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.whz.base.common.AppManager
import com.whz.base.utils.PictureUtils


/**
 *Created by whz  on 2019-06-24
 */
open class BaseActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
        PictureUtils.clearCache(this)
    }

    protected fun changeStatusBarTextColor(isBlack: Boolean) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//设置状态栏黑色字体
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE//恢复状态栏白色字体
            }
        }
    }


}