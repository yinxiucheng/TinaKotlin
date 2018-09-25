package com.tina.ordercenter.event

import com.tina.order.data.protocol.ShipAddress

/*
    选择收货人信息事件
 */
class SelectAddressEvent(val address: ShipAddress)
