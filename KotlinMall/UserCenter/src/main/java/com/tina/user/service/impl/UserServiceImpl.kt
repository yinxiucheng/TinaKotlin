package com.tina.user.service.impl

import com.tina.base.net.protocol.BaseResp
import com.tina.base.rx.BaseException
import com.tina.user.data.repository.UserRepository
import com.tina.user.service.UserService
import rx.Observable
import rx.functions.Func1
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
        val repository = UserRepository()

        return repository.register(mobile, pwd, verifyCode).flatMap(object :Func1<BaseResp<String>, Observable<Boolean>>{
            override fun call(t: BaseResp<String>): Observable<Boolean> {
                if (t.status != 0){
                    return Observable.error(BaseException(t.status, t.msg))
                }
                return Observable.just(true)
            }
        })
    }


}