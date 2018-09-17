package com.tina.base.presenter

import android.content.Context
import com.tina.base.presenter.view.BaseView
import com.tina.base.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
open class BasePresenter<T:BaseView> {
    lateinit var mView:T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context:Context

    fun checkNetWork():Boolean{
        if (NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}