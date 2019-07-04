package com.whz.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 *Created by whz  on 2019-06-26
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentSing