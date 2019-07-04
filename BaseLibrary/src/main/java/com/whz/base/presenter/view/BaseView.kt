package com.whz.base.presenter.view

/**
 *Created by whz on 2019-06-24
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
}