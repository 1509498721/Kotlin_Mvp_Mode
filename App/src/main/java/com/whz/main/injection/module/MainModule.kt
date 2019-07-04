package com.whz.main.injection.module

import com.whz.main.service.MainService
import com.whz.main.service.impl.MainServiceImpl
import dagger.Module
import dagger.Provides

/**
 *Created by whz  on 2019-06-26
 */
@Module
class MainModule {
    @Provides
    fun providesUserService(mainServiceImpl: MainServiceImpl):MainService{
        return mainServiceImpl
    }
}