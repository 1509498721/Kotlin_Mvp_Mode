package com.whz.usercenter.data.repository

import com.whz.base.data.net.RetrofitFactory
import com.whz.base.data.protocol.BaseResp
import com.whz.usercenter.data.api.UserApi
import com.whz.usercenter.data.bean.LoginBean
import io.reactivex.Observable
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class UserRepository @Inject constructor(){
    fun onLogin(mobile: String, password: String): Observable<BaseResp<LoginBean>> {
        return RetrofitFactory.instance.create(UserApi::class.java).onLogin(mobile, password)
    }
}