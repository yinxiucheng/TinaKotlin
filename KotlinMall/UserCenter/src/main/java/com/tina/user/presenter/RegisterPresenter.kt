package com.tina.user.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.user.presenter.view.RegisterView
import com.tina.user.service.impl.UserServiceImpl

/**
 * @author yxc
 * @date 2018/9/15
 */
class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(mobile: String, verifyCode: String, pwd: String) {
        /*
            业务逻辑
         */
        val userService = UserServiceImpl()
        userService.register(mobile, verifyCode, pwd)
                .execute(object : BaseSubscribler<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(true);
                    }
                })

    }


    fun login(mobile: String,pwd: String) {
        val userService = UserServiceImpl()
        userService.login(mobile, pwd)
                .execute(object : BaseSubscribler<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(true);
                    }
                })

    }
}