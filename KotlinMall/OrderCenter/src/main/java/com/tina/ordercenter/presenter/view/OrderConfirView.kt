package com.tina.ordercenter.presenter.view

import com.tina.base.presenter.view.BaseView
import com.tina.order.data.protocol.Order

/**
 * @author yxc
 * @date 2018/9/24
 */
interface OrderConfirView : BaseView {

    fun onGetOrderByIdResult(order: Order)

    //提交订单回调
    fun onSubmitOrderResult(result:Boolean)
}