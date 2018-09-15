package com.tina.base.ui

import com.tina.base.presenter.BasePresenter
import com.tina.base.presenter.view.BaseView

/**
 * @author yxc
 * @date 2018/9/15
 */
open class BaseMvpActivity<T:BasePresenter<*>>: BaseActivity(), BaseView {

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {

    }

    lateinit var mPresenter:T

}