package com.tina.user.data.api

import com.tina.base.data.net.protocol.BaseResp
import com.tina.user.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * @author yxc
 * @date 2018/9/16
 */
interface UserApi {
    /*
      用户注册
   */
    @POST("userCenter/register")
    fun register(@Body req:RegisterReq):Observable<BaseResp<String>>
}