package com.whz.base.injection.component

import android.content.Context
import com.whz.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 *Created by whz  on 2019-06-26
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun context():Context
}