package com.whz.usercenter.data.bean

/**
 *Created by whz  on 2019-06-26
 */
data class LoginBean(
    var sessionId: String? = null,
    var storeStatus: Int = 0,
    var alias: String? = null,
    var identifier: String? = null,
    var userSig: String? = null,
    var hasPayPassword: Boolean = false
)