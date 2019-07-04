package com.whz.main.service.impl

import com.whz.base.ext.convert
import com.whz.main.data.bean.HomeMessage
import com.whz.main.data.repository.MainRepository
import com.whz.main.service.MainService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class MainServiceImpl @Inject constructor() : MainService {


    @Inject
    lateinit var mainRepository: MainRepository

    override fun getBackgroundService(): Observable<String> {

        return mainRepository.onGetBackground().convert()
    }

    override fun getHomeMessageService(type: Int): Observable<List<HomeMessage>> {
        return mainRepository.onHomeMessage(type).convert()
    }
}