package com.whz.shopping.service.impl

import com.whz.base.ext.convert
import com.whz.shopping.data.bean.CartList
import com.whz.shopping.data.repository.ShoppingRepository
import com.whz.shopping.service.ShoppingService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class ShoppingServiceImpl @Inject constructor() : ShoppingService {



    @Inject
    lateinit var shoppingRepository: ShoppingRepository

    override fun getShoppingCarListService(): Observable<CartList> {
        return shoppingRepository.onGetShoppingCarList().convert()
    }
}