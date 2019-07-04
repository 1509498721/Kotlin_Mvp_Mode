package com.whz.base.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 *Created by whz  on 2019-06-26
 */
@Module
class ActivityModule(private val activity:Activity) {
    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}