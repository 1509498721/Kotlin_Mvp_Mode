package com.whz.main.presenter

import com.whz.base.ext.execute
import com.whz.base.presenter.BasePresenter
import com.whz.base.rx.BaseObserver
import com.whz.main.presenter.view.SplashView
import com.whz.main.service.MainService
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class SplashPresenter @Inject constructor() : BasePresenter<SplashView>() {

    @Inject
    lateinit var mainService: MainService


    fun getBackground() {
        if (!isNetWork()) {
            return
        }
        mainService.getBackgroundService()
            .execute(object : BaseObserver<String>(mView) {
                override fun onNext(t: String) {
                    mView.getBackground(t)
                }
            }, lifecycleProvider)
    }
}