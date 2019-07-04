package com.whz.base.data.net

import com.alibaba.android.arouter.facade.model.RouteMeta.build
import com.orhanobut.hawk.Hawk
import com.whz.base.common.BaseConstant
import com.whz.base.gson.MyGsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 *Created by whz  on 2019-06-24
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init {
        interceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Contern-Type", "application/json")
                .addHeader("charset", "utf-8")
                .addHeader("sessionId", Hawk.get(BaseConstant.SESSION_ID, ""))
                /* .addHeader("clientType", "0")
                 .addHeader("userId", AppPrefsUtils.getString(USER_ID))
                 .addHeader("language", AppPrefsUtils.getString(ISLANGUAGE))*/
                .build()
            chain.proceed(request)
        }
        retrofit = Retrofit.Builder()
            .baseUrl(BaseConstant.SERVER_ADDRESS)
            .addConverterFactory(MyGsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()
    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(initLoginInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private fun initLoginInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}