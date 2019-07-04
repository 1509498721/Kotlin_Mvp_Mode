package com.whz.found.data.api

import com.whz.base.data.protocol.BaseResp
import com.whz.found.common.FoundApiInfo
import com.whz.found.data.bean.Exploration
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *Created by whz  on 2019-06-26
 */
interface FoundApi {

    /**
     * 发现列表
     */
    @FormUrlEncoded
    @POST(FoundApiInfo.COMMENTLIST)
    fun commentList(
        @Field("pageNum") pageNum: Int,
        @Field("pageSize") pageSize: Int
    ): Observable<BaseResp<List<Exploration>>>
}