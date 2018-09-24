package com.tina.base.injection.component

import android.app.Activity
import android.content.Context
import com.tina.base.injection.ActivityScope
import com.tina.base.injection.module.ActivityModule
import com.tina.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * @author yxc
 * @date 2018/9/16
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class,LifecycleProviderModule::class))
interface ActivityComponent {

    fun activity():Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}