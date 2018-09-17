package com.tina.user.data.repository

import com.tina.base.data.net.RetrofitFactory
import com.tina.base.data.net.protocol.BaseResp
import com.tina.user.data.api.UserApi
import com.tina.user.data.protocol.RegisterReq
import rx.Observable
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/16
 */
class UserRepository @Inject constructor() {

    fun register(mobile:String, pwd:String, verifyCode:String):Observable<BaseResp<String>>{

        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
    }
}