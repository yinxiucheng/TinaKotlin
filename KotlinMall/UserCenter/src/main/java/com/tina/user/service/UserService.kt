package com.tina.user.service

import rx.Observable

/**
 * @author yxc
 * @date 2018/9/15
 */
interface UserService {
    fun register(mobile:String, verifyCode:String, pwd:String): Observable<Boolean>

    fun login(mobile:String, pwd:String): Observable<Boolean>
}