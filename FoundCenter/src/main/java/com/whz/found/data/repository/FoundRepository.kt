package com.whz.found.data.repository

import com.whz.base.data.net.RetrofitFactory
import com.whz.base.data.protocol.BaseResp
import com.whz.found.data.api.FoundApi
import com.whz.found.data.bean.Exploration
import io.reactivex.Observable
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class FoundRepository @Inject constructor(){
    fun onCommentList(pageNum: Int, pageSize: Int): Observable<BaseResp<List<Exploration>>> {
        return RetrofitFactory.instance.create(FoundApi::class.java).commentList(pageNum,pageSize)
    }
}