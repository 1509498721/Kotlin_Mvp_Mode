package com.whz.base.rx

import com.whz.base.common.BaseConstant
import com.whz.base.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 *Created by whz  on 2019-06-26
 */
class BaseFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (t.code != BaseConstant.API_SUCCESS) {
            return Observable.error(
                BaseException(
                    t.code,
                    t.message
                )
            )
        }
        return Observable.just(t.data)
    }

}