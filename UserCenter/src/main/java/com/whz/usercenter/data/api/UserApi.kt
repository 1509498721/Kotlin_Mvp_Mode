package com.whz.usercenter.data.api

import com.whz.base.data.protocol.BaseResp
import com.whz.usercenter.common.Api
import com.whz.usercenter.data.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *Created by whz  on 2019-06-26
 */
interface UserApi {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST(Api.LOGIN_API)
    fun onLogin(
        @Field("mobile") mobile: String,
        @Field("password") password: String
    ): Observable<BaseResp<LoginBean>>
}