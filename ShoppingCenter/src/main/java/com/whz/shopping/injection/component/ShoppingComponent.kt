package com.whz.shopping.injection.component

import com.whz.base.injection.PerComponentSing
import com.whz.base.injection.component.ActivityComponent
import com.whz.shopping.injection.module.ShoppingModule
import com.whz.shopping.ui.fragment.ShoppingCartFragment
import dagger.Component

/**
 *Created by whz  on 2019-06-26
 */
@PerComponentSing
@Component(dependencies = [ActivityComponent::class],modules = [ShoppingModule::class])
interface ShoppingComponent {
    fun inject(shoppingCarFragment: ShoppingCartFragment)
}