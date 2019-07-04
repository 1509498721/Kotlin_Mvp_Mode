package com.whz.shopping.data.api

import com.whz.base.data.protocol.BaseResp
import com.whz.shopping.common.ShoppingApiInfo
import com.whz.shopping.data.bean.CartList
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *Created by whz  on 2019-06-26
 */
interface ShoppingApi {

    /**
     * 购物车列表
     */
    @POST(ShoppingApiInfo.SHOPPING_CAR_API)
    fun getShoppingCarList(): Observable<BaseResp<CartList>>
}