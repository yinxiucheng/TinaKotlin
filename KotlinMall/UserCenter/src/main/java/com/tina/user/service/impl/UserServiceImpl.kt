package com.tina.user.service.impl

import com.tina.base.data.net.protocol.BaseResp
import com.tina.base.ext.convertBoolean
import com.tina.base.rx.BaseException
import com.tina.base.rx.BaseFunBoolean
import com.tina.user.data.repository.UserRepository
import com.tina.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class UserServiceImpl @Inject constructor() : UserService {
    override fun login(mobile: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        return repository.register(mobile, pwd, verifyCode)
                .convertBoolean()
    }
}