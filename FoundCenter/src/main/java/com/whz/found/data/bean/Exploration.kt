package com.whz.found.data.bean

/**
 *Created by whz  on 2019-06-28
 */
data class Exploration(
    var goodsDefaultImage: String? = null,
    var goodsName: String? = null,
    var goodsPraiseNum: Int = 0,
    var id: Long = 0,
    var shopCommentNum: Int = 0,
    var hasPraiseRole: Boolean = false,
    var hasPraise: Boolean = false
)