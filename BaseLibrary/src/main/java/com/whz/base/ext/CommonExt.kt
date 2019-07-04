package com.whz.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.trello.rxlifecycle2.LifecycleProvider
import com.whz.base.data.protocol.BaseResp
import com.whz.base.rx.BaseFunc
import com.whz.base.rx.BaseFuncBoolean
import com.whz.base.rx.BaseObserver
import com.whz.base.utils.glide.GlideUtil
import com.whz.base.widgets.DefaultTextWatcher
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Observable


/**
 *Created by whz  on 2019-06-24
 */


fun <T> Observable<T>.execute(subscriber: BaseObserver<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
        .compose(lifecycleProvider.bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}

fun ImageView.loadUrl(url: String) {
    GlideUtil.load(url, this)
}

//fun <T> Observable<T>.execute(subscriber: BaseObserver<T>) {
//    this.observeOn(AndroidSchedulers.mainThread())
//        .subscribeOn(Schedulers.io())
//        .subscribe(subscriber)
//}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun View.OnClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.OnClick(method: () -> Unit) {
    this.setOnClickListener {
        method()
    }
}

fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}