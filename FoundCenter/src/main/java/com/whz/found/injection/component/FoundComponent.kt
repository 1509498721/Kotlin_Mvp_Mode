package com.whz.found.injection.component

import com.whz.base.injection.PerComponentSing
import com.whz.base.injection.component.ActivityComponent
import com.whz.found.injection.module.FoundModule
import com.whz.found.ui.fragment.FoundFragment
import dagger.Component

/**
 *Created by whz  on 2019-06-26
 */
@PerComponentSing
@Component(dependencies = [ActivityComponent::class],modules = [FoundModule::class])
interface FoundComponent {
    fun inject(foundFragment: FoundFragment)
}