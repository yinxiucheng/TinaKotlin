package com.tina.user.service.impl

import com.tina.user.service.UserService
import rx.Observable
import java.util.*

/**
 * @author yxc
 * @date 2018/9/15
 */
class UserServiceImpl : UserService {
    override fun login(mobile: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return Observable.just(true)
    }


}