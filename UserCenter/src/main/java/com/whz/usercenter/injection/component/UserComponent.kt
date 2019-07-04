package com.whz.usercenter.injection.component

import com.whz.base.injection.PerComponentSing
import com.whz.base.injection.component.ActivityComponent
import com.whz.usercenter.injection.module.UserModule
import com.whz.usercenter.ui.activity.LoginActivity
import dagger.Component

/**
 *Created by whz  on 2019-06-26
 */
@PerComponentSing
@Component(dependencies = [ActivityComponent::class],modules = [UserModule::class])
interface UserComponent {
    fun inject(loginActivity: LoginActivity)
}