package com.whz.shopping.injection.module

import com.whz.shopping.service.ShoppingService
import com.whz.shopping.service.impl.ShoppingServiceImpl
import dagger.Module
import dagger.Provides

/**
 *Created by whz  on 2019-06-26
 */
@Module
class ShoppingModule {
    @Provides
    fun providesFoundService(shoppingServiceImpl: ShoppingServiceImpl): ShoppingService {
        return shoppingServiceImpl
    }
}