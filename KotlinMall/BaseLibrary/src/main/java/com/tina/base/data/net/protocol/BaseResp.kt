package com.tina.base.data.net.protocol

/**
 * @author yxc
 * @date 2018/9/15
 */
class BaseResp<out T>(val status:Int, val message:String, val data:T)