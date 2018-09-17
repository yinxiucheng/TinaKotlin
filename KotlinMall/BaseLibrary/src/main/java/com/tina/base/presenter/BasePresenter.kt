package com.tina.base.presenter

import com.tina.base.presenter.view.BaseView
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
}