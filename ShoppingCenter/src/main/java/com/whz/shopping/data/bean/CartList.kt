package com.whz.shopping.data.bean

/**
 *Created by whz  on 2019-06-29
 */
data class CartList(
    var isSelect: Boolean? = false,
    var storeName: String? = null,
    var cartList: List<CartListBean>? = null
)

data class CartListBean(
    var cartId: String? = null,
    var commentnum: String? = null,
    var count: Int = 0,
    var goodsDesc: String? = null,
    var goodsSpecId: String? = null,
    var image: String? = null,
    var itemId: String? = null,
    var price: Double = 0.toDouble(),
    var salenum: String? = null,
    var specInfo: String? = null,
    var title: String? = null
)