package com.kotlin.goods.data.protocol

import tina.com.goods.data.protocol.CartGoods

/*
    提交购物车
 */
data class SubmitCartReq(val goodsList: List<CartGoods>, val totalPrice: Long)
