package com.whz.base.task

import com.orhanobut.hawk.Hawk
import com.whz.base.utils.launchstarter.task.Task

/**
 *Created by whz  on 2019-06-28
 */
class InitHawkTask:Task() {
    override fun run() {
        Hawk.init(mContext).build()
    }
}