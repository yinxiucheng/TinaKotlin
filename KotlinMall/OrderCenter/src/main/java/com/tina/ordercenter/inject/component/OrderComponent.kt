package com.kotlin.order.injection.component

import com.kotlin.order.injection.module.OrderModule
import com.tina.base.injection.PerComponentScope
import com.tina.base.injection.component.ActivityComponent
import com.tina.ordercenter.ui.activity.OrderConfirmActivity
import com.tina.ordercenter.ui.activity.OrderDetailActivity
import com.tina.ordercenter.ui.fragment.OrderFragment
import dagger.Component

/*
    订单Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
    fun inject(fragment: OrderFragment)
    fun inject(activity: OrderDetailActivity)

}
