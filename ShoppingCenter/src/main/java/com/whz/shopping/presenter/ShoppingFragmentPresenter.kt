package com.whz.shopping.presenter

import com.whz.base.ext.execute
import com.whz.base.presenter.BasePresenter
import com.whz.base.rx.BaseObserver
import com.whz.shopping.data.bean.CartList
import com.whz.shopping.presenter.view.ShoppingFragmentView
import com.whz.shopping.service.ShoppingService
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class ShoppingFragmentPresenter @Inject constructor() : BasePresenter<ShoppingFragmentView>() {

    @Inject
    lateinit var shoppingService: ShoppingService


    fun getshoppingCarList() {
        if (!isNetWork()) {
            return
        }
        shoppingService.getShoppingCarListService()
            .execute(object : BaseObserver<CartList>(mView) {
                override fun onNext(t: CartList) {
                    mView.getshoppingCarList(t)
                }
            }, lifecycleProvider)
    }
}