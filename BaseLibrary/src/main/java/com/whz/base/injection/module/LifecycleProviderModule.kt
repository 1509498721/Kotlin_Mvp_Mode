package com.whz.base.injection.module

import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 *Created by whz  on 2019-06-26
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {
    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> {
        return lifecycleProvider
    }
}