package com.whz.main.data.api

import com.whz.base.data.protocol.BaseResp
import com.whz.main.common.MainApiInfo
import com.whz.main.data.bean.HomeMessage
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *Created by whz  on 2019-06-26
 */
interface MainApi {

    /**
     * 获取背景
     */
    @POST(MainApiInfo.GET_BACKGROUND_API)
    fun onGetBackground(): Observable<BaseResp<String>>

    /**
     * 获取首页消息
     */
    @FormUrlEncoded
    @POST(MainApiInfo.GET_HOME_MESSAGE_API)
    fun onHomeMessage(@Field("type") type: Int): Observable<BaseResp<List<HomeMessage>>>
}