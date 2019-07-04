package com.whz.base.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.whz.base.R
import com.whz.base.common.BaseApplication
import com.whz.base.injection.component.ActivityComponent
import com.whz.base.injection.component.DaggerActivityComponent
import com.whz.base.injection.module.ActivityModule
import com.whz.base.injection.module.LifecycleProviderModule
import com.whz.base.presenter.BasePresenter
import com.whz.base.presenter.view.BaseView
import com.whz.base.widgets.MyPopupWindow
import com.whz.base.widgets.ProgressLoading
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-24
 */
open abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {


    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent

    lateinit var mProgressLoading: ProgressLoading

    //顶部弹出toast
    lateinit var popupWindow: MyPopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressLoading = ProgressLoading.create(this)
        popupWindow = MyPopupWindow(this)
        popupWindow.fitPopupWindowOverStatusBar(true)
        initActivityInjection()
        injectComponent()
        initView()
        initData()
    }

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent
            .builder()
            .appComponent((application as BaseApplication).appCompoent)
            .activityModule(ActivityModule(this))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }

    abstract fun injectComponent()
    abstract fun initView()
    abstract fun initData()

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out)
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        super.startActivity(intent, options)
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out)
    }

    fun topShow(text: String) {
        popupWindow.show(text)
    }

    override fun showLoading() {
        mProgressLoading.showLoad()
    }

    override fun hideLoading() {

        mProgressLoading.hideLoad()
    }

    override fun onError(text: String) {
        toast(text)
    }
}