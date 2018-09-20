package com.tina.base.injection.module

import android.app.Activity
import android.support.v4.app.FragmentActivity
import com.tina.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * @author yxc
 * @date 2018/9/16
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun providesActivity(): Activity{
        return activity
    }
}