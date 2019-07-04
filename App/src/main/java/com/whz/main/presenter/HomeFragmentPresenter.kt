package com.whz.main.presenter

import com.whz.base.ext.execute
import com.whz.base.presenter.BasePresenter
import com.whz.base.rx.BaseObserver
import com.whz.main.data.bean.HomeMessage
import com.whz.main.presenter.view.HomeFragmentView
import com.whz.main.service.MainService
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class HomeFragmentPresenter @Inject constructor() : BasePresenter<HomeFragmentView>() {

    @Inject
    lateinit var mainService: MainService


    fun getHomeMssage(type: Int) {
        if (!isNetWork()) {
            return
        }
        mainService.getHomeMessageService(type)
            .execute(object : BaseObserver<List<HomeMessage>>(mView) {
                override fun onNext(t: List<HomeMessage>) {
                    mView.getHomeMessage(t)
                }
            }, lifecycleProvider)
    }
}