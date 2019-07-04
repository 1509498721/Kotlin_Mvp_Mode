package com.whz.shopping.data.repository

import com.whz.base.data.net.RetrofitFactory
import com.whz.base.data.protocol.BaseResp
import com.whz.shopping.data.api.ShoppingApi
import com.whz.shopping.data.bean.CartList
import io.reactivex.Observable
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class ShoppingRepository @Inject constructor(){
    fun onGetShoppingCarList(): Observable<BaseResp<CartList>> {
        return RetrofitFactory.instance.create(ShoppingApi::class.java).getShoppingCarList()
    }
}