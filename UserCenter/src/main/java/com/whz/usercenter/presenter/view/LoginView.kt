package com.whz.usercenter.presenter.view

import com.whz.base.presenter.view.BaseView
import com.whz.usercenter.data.bean.LoginBean

/**
 *Created by whz  on 2019-06-26
 */
interface LoginView:BaseView {
    fun onLoginResult (loginBean:LoginBean)
}