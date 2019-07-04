package com.whz.main.service
import com.whz.main.data.bean.HomeMessage
import io.reactivex.Observable
/**
 *Created by whz  on 2019-06-26
 */
interface MainService {
    fun getBackgroundService(): Observable<String>
    fun getHomeMessageService(type: Int): Observable<List<HomeMessage>>
}