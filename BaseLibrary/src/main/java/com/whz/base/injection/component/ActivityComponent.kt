package com.whz.base.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.whz.base.injection.ActivitySing
import com.whz.base.injection.module.ActivityModule
import com.whz.base.injection.module.LifecycleProviderModule
import dagger.Component

/**
 *Created by whz  on 2019-06-26
 */
@ActivitySing
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class, LifecycleProviderModule::class])
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}