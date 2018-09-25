package com.tina.pay.injection.module

import com.tina.paysdk.service.PayService
import com.tina.paysdk.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

/*
    支付 Module
 */
@Module
class PayModule {

    @Provides
    fun providePayService(payService: PayServiceImpl): PayService {
        return payService
    }

}
