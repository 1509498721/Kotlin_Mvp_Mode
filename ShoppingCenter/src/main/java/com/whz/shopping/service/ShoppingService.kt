package com.whz.shopping.service

import com.whz.shopping.data.bean.CartList
import io.reactivex.Observable

/**
 *Created by whz  on 2019-06-26
 */
interface ShoppingService {
    fun getShoppingCarListService(): Observable<CartList>
}