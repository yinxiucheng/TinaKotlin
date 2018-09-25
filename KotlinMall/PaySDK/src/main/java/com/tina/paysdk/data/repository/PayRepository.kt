package com.kotlin.pay.data.repository


import com.kotlin.pay.data.api.PayApi
import com.kotlin.pay.data.protocol.GetPaySignReq
import com.kotlin.pay.data.protocol.PayOrderReq
import com.tina.base.data.net.RetrofitFactory
import com.tina.base.data.net.protocol.BaseResp
import rx.Observable
import javax.inject.Inject


/*
   支付数据层
 */
class PayRepository @Inject constructor() {

    /*
        获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).getPaySign(GetPaySignReq(orderId, totalPrice))
    }

    /*
        刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).payOrder(PayOrderReq(orderId))
    }


}
