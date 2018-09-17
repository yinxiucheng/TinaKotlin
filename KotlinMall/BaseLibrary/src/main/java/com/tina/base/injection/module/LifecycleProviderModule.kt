package com.tina.base.injection.module

import android.content.Context
import com.tina.base.common.BaseApplication
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author yxc
 * @date 2018/9/16
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun  providesLifecycleProvider():LifecycleProvider<*>{
        return lifecycleProvider
    }
}