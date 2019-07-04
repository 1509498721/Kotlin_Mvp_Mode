package com.whz.main.injection.component

import com.whz.base.injection.PerComponentSing
import com.whz.base.injection.component.ActivityComponent
import com.whz.main.injection.module.MainModule
import com.whz.main.ui.activity.SplashActivity
import com.whz.main.ui.fragment.HomeFragment
import dagger.Component

/**
 *Created by whz  on 2019-06-26
 */
@PerComponentSing
@Component(dependencies = [ActivityComponent::class], modules = [MainModule::class])
interface MainComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(homeFragment: HomeFragment)
}