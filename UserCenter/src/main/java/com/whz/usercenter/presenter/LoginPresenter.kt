package com.whz.usercenter.presenter

import com.whz.base.ext.execute
import com.whz.base.presenter.BasePresenter
import com.whz.base.rx.BaseObserver
import com.whz.base.utils.NetWorkUtils
import com.whz.usercenter.data.bean.LoginBean
import com.whz.usercenter.presenter.view.LoginView
import com.whz.usercenter.service.UserService
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService


    fun login(mobile: String, password: String) {
        if (!isNetWork()) {
            return
        }
        mView.showLoading()
        userService.loginService(mobile, password)
            .execute(object : BaseObserver<LoginBean>(mView) {
                override fun onNext(t: LoginBean) {
                    mView.onLoginResult(t)
                }
            }, lifecycleProvider)
    }
}