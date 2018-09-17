package com.tina.base.common

import android.app.Application
import com.tina.base.injection.component.AppComponent
import com.tina.base.injection.component.DaggerAppComponent
import com.tina.base.injection.module.AppModule
import org.jetbrains.anko.toast

/**
 * @author yxc
 * @date 2018/9/15
 */
open class BaseApplication: Application() {

     lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        toast("")
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}