package com.tina.user.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.user.presenter.view.RegisterView
import com.tina.user.service.UserService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, verifyCode: String, pwd: String) {
        /*
            业务逻辑
         */
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.register(mobile, verifyCode, pwd)
                .execute(object : BaseSubscribler<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t){
                            mView.onRegisterResult("注册成功")
                        }
                    }
                }, lifecycleProvider)
    }

}