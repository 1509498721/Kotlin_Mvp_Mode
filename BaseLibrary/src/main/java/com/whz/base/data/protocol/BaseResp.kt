package com.whz.base.data.protocol

import com.google.gson.annotations.SerializedName


/**
 *Created by whz  on 2019-06-26
 */

class BaseResp<out T>(val code:Int, val message:String, @SerializedName("object") val data: T)