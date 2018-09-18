package com.tina.user.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.user.data.protocol.UserInfo
import com.tina.user.presenter.view.LoginView
import com.tina.user.service.UserService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, pushId: String) {
        /*
            业务逻辑
         */
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.login(mobile,pwd, pushId)
                .execute(object : BaseSubscribler<UserInfo>(mView) {
                    override fun onNext(userInfo: UserInfo) {
                        mView.onLoginResult(userInfo)
                    }
                }, lifecycleProvider)
    }

}