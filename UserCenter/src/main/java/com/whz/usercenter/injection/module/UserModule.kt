package com.whz.usercenter.injection.module

import com.whz.usercenter.service.UserService
import com.whz.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 *Created by whz  on 2019-06-26
 */
@Module
class UserModule {
    @Provides
    fun providesUserService(userServiceImpl: UserServiceImpl):UserService{
        return userServiceImpl
    }
}