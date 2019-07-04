package com.whz.main.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.orhanobut.hawk.Hawk
import com.whz.base.ui.activity.BaseMvpActivity
import com.whz.main.R
import com.whz.main.injection.component.DaggerMainComponent
import com.whz.main.injection.module.MainModule
import com.whz.main.presenter.SplashPresenter
import com.whz.main.presenter.view.SplashView
import com.whz.base.utils.RxPermissionUtil
import com.whz.provider.common.HawkConstants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class SplashActivity : BaseMvpActivity<SplashPresenter>(), SplashView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    @SuppressLint("CheckResult")
    override fun getBackground(backgroundUrl: String) {
        Hawk.put(HawkConstants.HOMEBACKGROUND, backgroundUrl)
        RxPermissionUtil.with(this@SplashActivity)
            .request(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
            )
            .subscribe { permission ->
                val isFirst = Hawk.get(HawkConstants.FIRSTIN, true)

                if (permission) {
                    Observable.timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            if (isFirst) {
                                startActivity<GuideActivity>()
                                finish()
                            } else {
                                startActivity<MainActivity>()
                                finish()
                            }
                        }


                }
            }
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().activityComponent(mActivityComponent).mainModule(MainModule()).build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun initView() {

    }

    override fun initData() {
        mPresenter.getBackground()
    }


}
