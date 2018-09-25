package com.tina.pay.injection.component

import com.tina.base.injection.PerComponentScope
import com.tina.base.injection.component.ActivityComponent
import com.tina.pay.injection.module.PayModule
import com.tina.paysdk.ui.activity.CashRegisterActivity
import dagger.Component

/*
    支付Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(PayModule::class))
interface PayComponent {
    fun inject(activity: CashRegisterActivity)
}
