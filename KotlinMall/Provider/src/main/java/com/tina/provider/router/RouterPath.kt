package com.tina.provider.router

/**
 * @author yxc
 * @date 2018/9/24
 */
class RouterPath {

    class UserCenter{
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    //订单模块
    class OrderCenter{
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }


}