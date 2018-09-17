package com.tina.base.injection.component

import android.content.Context
import com.tina.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author yxc
 * @date 2018/9/16
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context():Context

}