package com.whz.shopping.presenter.view

import com.whz.base.presenter.view.BaseView
import com.whz.shopping.data.bean.CartList

/**
 *Created by whz  on 2019-06-26
 */
interface ShoppingFragmentView : BaseView {
    fun getshoppingCarList(shoppingCarList: CartList)
}