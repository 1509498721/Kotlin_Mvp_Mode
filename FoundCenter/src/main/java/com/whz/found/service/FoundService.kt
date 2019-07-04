package com.whz.found.service
import com.whz.found.data.bean.Exploration
import io.reactivex.Observable
/**
 *Created by whz  on 2019-06-26
 */
interface FoundService {
    fun commentListService(pageNum: Int, pageSize: Int): Observable<List<Exploration>>
}