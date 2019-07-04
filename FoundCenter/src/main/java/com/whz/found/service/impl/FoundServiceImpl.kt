package com.whz.found.service.impl

import com.whz.base.ext.convert
import com.whz.found.data.bean.Exploration
import com.whz.found.data.repository.FoundRepository
import com.whz.found.service.FoundService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class FoundServiceImpl @Inject constructor() : FoundService {



    @Inject
    lateinit var foundRepository: FoundRepository

    override fun commentListService(pageNum: Int, pageSize: Int): Observable<List<Exploration>> {
        return foundRepository.onCommentList(pageNum,pageSize).convert()
    }
}