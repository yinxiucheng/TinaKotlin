package com.tina.paysdk.presenter.view

import com.tina.base.presenter.view.BaseView

/*
    支付 视图回调
 */
interface PayView : BaseView {

    //获取支付签名
    fun onGetSignResult(result: String)
    //同步支付成功状态
    fun onPayOrderResult(result: Boolean)

}
