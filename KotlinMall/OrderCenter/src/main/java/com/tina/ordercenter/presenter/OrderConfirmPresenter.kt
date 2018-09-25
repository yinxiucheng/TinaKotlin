package com.tina.ordercenter.presenter

import com.kotlin.order.service.OrderService
import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.order.data.protocol.Order
import com.tina.ordercenter.presenter.view.OrderConfirView
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/24
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirView>() {

    @Inject
    lateinit var orderService: OrderService


    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId)
                .execute(object : BaseSubscribler<Order>(mView) {
                    override fun onNext(t: Order) {
                        mView.onGetOrderByIdResult(t)
                    }
                }, lifecycleProvider)
    }

    /**
     * 提交订单
     */
    fun submitOrder(order: Order){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order).execute(object : BaseSubscribler<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSubmitOrderResult(t)
            }
        }, lifecycleProvider)
    }
}