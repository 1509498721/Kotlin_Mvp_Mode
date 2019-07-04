package com.whz.base.rx

import com.whz.base.common.BaseConstant
import com.whz.base.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 *Created by whz  on 2019-06-26
 */
class BaseFuncBoolean<T> : Function<BaseResp<T>, Observable<Boolean>> {
    override fun apply(t: BaseResp<T>): Observable<Boolean> {
        if (t.code != BaseConstant.API_SUCCESS) {
            return Observable.error(
                BaseException(
                    t.code,
                    t.message
                )
            )
        }
        return Observable.just(true)
    }

}