package com.tina.user.data.repository

import com.tina.base.net.RetrofitFactory
import com.tina.base.net.protocol.BaseResp
import com.tina.user.data.api.UserApi
import com.tina.user.data.protocol.RegisterReq
import rx.Observable

/**
 * @author yxc
 * @date 2018/9/16
 */
class UserRepository {

    fun register(mobile:String, pwd:String, verifyCode:String):Observable<BaseResp<String>>{

        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
    }
}