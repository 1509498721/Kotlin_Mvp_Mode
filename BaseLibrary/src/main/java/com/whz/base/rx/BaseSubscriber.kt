package com.whz.base.rx

import com.whz.base.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *Created by whz  on 2019-06-24
 */
abstract class BaseObserver<T>(val view: BaseView) : Observer<T> {
    override fun onError(e: Throwable) {
        view.hideLoading()
        if (e is BaseException) {
            println("属于定义错误返回" + e.mess)
            view.onError(e.mess)
            return
        }
        println("不属于定义错误返回$e")
    }

    override fun onComplete() {
        view.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }

}
