package com.tina.ordercenter.presenter.view

import com.tina.base.presenter.view.BaseView
import com.tina.order.data.protocol.Order

/*
    订单详情页 视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}
