package com.tina.base

import android.app.Application
import org.jetbrains.anko.toast

/**
 * @author yxc
 * @date 2018/9/15
 */
open class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        toast("")
    }
}