package com.whz.base.injection.module

import android.content.Context
import com.whz.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *Created by whz  on 2019-06-26
 */
@Module
class AppModule(private val context:BaseApplication) {
    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}