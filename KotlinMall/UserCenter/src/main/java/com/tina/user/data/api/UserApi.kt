package com.tina.user.data.api

import com.tina.base.data.net.protocol.BaseResp
import com.tina.user.data.protocol.*
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


    @POST("userCenter/login")
    fun login(@Body req:LoginReq):Observable<BaseResp<UserInfo>>


    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req:ForgetPwdReq):Observable<BaseResp<String>>


    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req:ResetPwdReq):Observable<BaseResp<String>>

    /*
         编辑用户资料
      */
    @POST("userCenter/editUser")
    fun editUser(@Body req:EditUserReq):Observable<BaseResp<UserInfo>>
}