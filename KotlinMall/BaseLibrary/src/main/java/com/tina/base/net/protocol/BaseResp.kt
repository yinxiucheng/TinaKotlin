package com.tina.base.net.protocol

/**
 * @author yxc
 * @date 2018/9/15
 */
class BaseResp<out T>(val status:Int, val msg:String, val data:T)