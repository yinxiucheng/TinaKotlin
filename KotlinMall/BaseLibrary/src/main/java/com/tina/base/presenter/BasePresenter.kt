package com.tina.base.presenter

import com.tina.base.presenter.view.BaseView

/**
 * @author yxc
 * @date 2018/9/15
 */
open class BasePresenter<T:BaseView> {
    lateinit var mView:T
}