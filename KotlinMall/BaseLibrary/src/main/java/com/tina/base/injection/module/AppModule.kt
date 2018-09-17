package com.tina.base.injection.module

import android.content.Context
import com.tina.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author yxc
 * @date 2018/9/16
 */
@Module
class AppModule(private val context:BaseApplication) {

    @Singleton
    @Provides
    fun  providesContext():Context{
        return context
    }
}