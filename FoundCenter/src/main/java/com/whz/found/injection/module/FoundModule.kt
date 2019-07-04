package com.whz.found.injection.module

import com.whz.found.service.FoundService
import com.whz.found.service.impl.FoundServiceImpl
import dagger.Module
import dagger.Provides

/**
 *Created by whz  on 2019-06-26
 */
@Module
class FoundModule {
    @Provides
    fun providesFoundService(foundServiceImpl: FoundServiceImpl): FoundService {
        return foundServiceImpl
    }
}