package com.tina.ordercenter.presenter

import com.kotlin.order.service.OrderService
import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.order.data.protocol.Order
import com.tina.ordercenter.presenter.view.OrderListView
import javax.inject.Inject

/*
    订单列表Presenter
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderService: OrderService

    /*
        根据订单状态获取订单列表
     */
    fun getOrderList(orderStatus: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderList(orderStatus).execute(object : BaseSubscribler<MutableList<Order>?>(mView) {
            override fun onNext(t: MutableList<Order>?) {
                    mView.onGetOrderListResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        订单确认收货
     */
    fun confirmOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.confirmOrder(orderId).execute(object : BaseSubscribler<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onConfirmOrderResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        取消订单
     */
    fun cancelOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.cancelOrder(orderId).execute(object : BaseSubscribler<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onCancelOrderResult(t)
            }
        }, lifecycleProvider)

    }



}
