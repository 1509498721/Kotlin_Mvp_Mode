package com.whz.main.data.repository

import com.whz.base.data.net.RetrofitFactory
import com.whz.base.data.protocol.BaseResp
import com.whz.main.data.api.MainApi
import com.whz.main.data.bean.HomeMessage
import io.reactivex.Observable
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class MainRepository @Inject constructor(){
    fun onGetBackground(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(MainApi::class.java).onGetBackground()
    }

    fun onHomeMessage(type: Int): Observable<BaseResp<List<HomeMessage>>> {
        return RetrofitFactory.instance.create(MainApi::class.java).onHomeMessage(type)
    }
}