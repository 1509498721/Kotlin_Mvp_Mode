package com.whz.base.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*
import kotlin.system.exitProcess

/**
 *Created by whz  on 2019-06-27
 */
class AppManager private constructor() {

    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }
    /**
     * 获取当前的Activity
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }
    /**
     * 结束所有除目标外的Activity
     */
    fun finishActivitiesWithoutTarget(cls: Class<*>) {
        val tempActivityStack = Stack<Activity>()
        tempActivityStack.addAll(activityStack)
        for (activity in tempActivityStack) {
            if (activity.javaClass != cls) {
                activity.finish()
            }
        }
        tempActivityStack.clear()
    }

    fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

    @SuppressLint("MissingPermission")
    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManage = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManage.killBackgroundProcesses(context.packageName)
        exitProcess(status = 0)
    }
}