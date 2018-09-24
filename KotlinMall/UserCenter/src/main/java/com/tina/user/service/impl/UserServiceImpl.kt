package com.tina.user.service.impl

import com.tina.base.ext.convert
import com.tina.base.ext.convertBoolean
import com.tina.user.data.protocol.UserInfo
import com.tina.user.data.repository.UserRepository
import com.tina.user.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class UserServiceImpl @Inject constructor() : UserService {


    @Inject
    lateinit var repository: UserRepository

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId).convert()
    }

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode).convertBoolean()
    }


    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile, verifyCode).convertBoolean()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile, pwd).convertBoolean()
    }

    /**
     * 修改用户资料
     */
    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo> {
        return repository.editUser(userIcon, userName, userGender, userSign).convert()
    }


}