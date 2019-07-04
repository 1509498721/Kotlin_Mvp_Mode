package com.whz.base.task

import com.whz.base.utils.glide.GlideUtil
import com.whz.base.utils.launchstarter.task.Task

/**
 *Created by whz  on 2019-06-28
 */
class InitGlideTask : Task() {
    override fun run() {
        GlideUtil.init(mContext)
    }
}