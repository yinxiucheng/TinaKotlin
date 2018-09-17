package com.tina.base.presenter.view

/**
 * @author yxc
 * @date 2018/9/15
 */
interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun onError(string: String)
}