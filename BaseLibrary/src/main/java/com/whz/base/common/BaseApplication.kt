package com.whz.base.common

import android.app.Application
import android.content.Context
import com.whz.base.injection.component.AppComponent
import com.whz.base.injection.component.DaggerAppComponent
import com.whz.base.injection.module.AppModule
import com.whz.base.task.InitGlideTask
import com.whz.base.task.InitHawkTask
import com.whz.base.utils.launchstarter.TaskDispatcher

/**
 *Created by whz  on 2019-06-26
 */
class BaseApplication : Application() {
    lateinit var appCompoent: AppComponent
    lateinit var taskDispatcher: TaskDispatcher

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initAppInjection()
        initTask()
    }

    private fun initTask() {
        TaskDispatcher.init(context)
        taskDispatcher = TaskDispatcher.createInstance()
        taskDispatcher.addTask(InitGlideTask())
            .addTask(InitHawkTask())
            .start()
        taskDispatcher.await()
    }

    private fun initAppInjection() {
        appCompoent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}