package com.tina.ordercenter.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.order.injection.component.DaggerOrderComponent
import com.kotlin.order.injection.module.OrderModule
import com.tina.base.ui.activity.BaseMvpActivity
import com.tina.order.data.protocol.Order
import com.tina.ordercenter.R
import com.tina.ordercenter.presenter.OrderConfirmPresenter
import com.tina.ordercenter.presenter.view.OrderConfirView
import com.tina.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*

/**
 * @author yxc
 * @date 2018/9/24
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
        initView()
    }

    private fun initView() {

    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent)
                .orderModule(OrderModule()).build().inject(this)

        mPresenter.mView = this
    }

    override fun onGetOrder(order: Order) {
        mTotalPriceTv.text = "合计： ${order.totalPrice}"
    }

}