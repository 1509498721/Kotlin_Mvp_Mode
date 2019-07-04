package com.whz.base.gson

import com.google.gson.Gson
import com.whz.base.rx.BaseException
import okhttp3.ResponseBody
import java.io.IOException
import java.lang.reflect.Type
import retrofit2.Converter
/**
 *Created by whz  on 2019-06-24
 */
class MyGsonResponseBodyConverter <T>(private val gson: Gson, private val type: Type) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val response = value.string()
        try {
            val beanError = gson.fromJson(response, ErrorBean::class.java)
            if (beanError.code != 1) {
                throw BaseException(beanError.code, beanError.message!!)
            }
            return gson.fromJson(response, type)
        } finally {
            value.close()
        }
    }
}