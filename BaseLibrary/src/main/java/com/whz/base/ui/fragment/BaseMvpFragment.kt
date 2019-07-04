package com.whz.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whz.base.common.BaseApplication
import com.whz.base.injection.component.ActivityComponent
import com.whz.base.injection.component.DaggerActivityComponent
import com.whz.base.injection.module.ActivityModule
import com.whz.base.injection.module.LifecycleProviderModule
import com.whz.base.presenter.BasePresenter
import com.whz.base.presenter.view.BaseView
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-24
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    private var rootView: View? = null

    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent
            .builder()
            .appComponent((activity!!.application as BaseApplication).appCompoent)
            .activityModule(ActivityModule(activity!!))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }

    abstract fun injectComponent()

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
        activity!!.toast(text)
    }
}