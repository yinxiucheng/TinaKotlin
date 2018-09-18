package com.tina.user.service

import com.tina.user.data.protocol.UserInfo
import rx.Observable

/**
 * @author yxc
 * @date 2018/9/15
 */
interface UserService {

    fun register(mobile:String, verifyCode:String, pwd:String): Observable<Boolean>

    fun login(mobile:String, pwd:String, pushId: String): Observable<UserInfo>

    fun forgetPwd(mobile:String, verifyCode: String): Observable<Boolean>

    fun resetPwd(mobile:String, pwd:String): Observable<Boolean>
}
