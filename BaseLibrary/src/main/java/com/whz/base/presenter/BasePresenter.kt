package com.whz.base.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.whz.base.presenter.view.BaseView
import com.whz.base.utils.NetWorkUtils
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-24
 */
open class BasePresenter<T : BaseView> {

    lateinit var mView: T

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    fun isNetWork(): Boolean {
        if (NetWorkUtils.isWifiConnected(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}