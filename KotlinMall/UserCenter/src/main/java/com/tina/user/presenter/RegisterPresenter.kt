package com.tina.user.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.user.presenter.view.RegisterView
import com.tina.user.service.UserService
import com.tina.user.service.impl.UserServiceImpl
import retrofit2.http.Field
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * @author yxc
 * @date 2018/9/15
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    @field:[Named("service")]
    lateinit var userService: UserService

    @Inject
    @field:[Named("service2")]
    lateinit var userService2: UserService

    fun register(mobile: String, verifyCode: String, pwd: String) {
        /*
            业务逻辑
         */
        mView.showLoading()
        userService.register(mobile, verifyCode, pwd)
                .execute(object : BaseSubscribler<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t){
                            mView.onRegisterResult("注册成功")
                        }
                        mView.hideLoading()

                    }
                }, lifecycleProvider)

    }


    fun register2(mobile: String, verifyCode: String, pwd: String) {
        /*
            业务逻辑
         */
        userService.register(mobile, verifyCode, pwd)
                .execute(object : BaseSubscribler<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t){

                        }
                        mView.onRegisterResult("注册成功")
                    }
                }, lifecycleProvider)

    }


//    fun login(mobile: String,pwd: String) {
//        val userService = UserServiceImpl()
//        userService.login(mobile, pwd)
//                .execute(object : BaseSubscribler<Boolean>() {
//                    override fun onNext(t: Boolean) {
//                        mView.onRegisterResult(true);
//                    }
//                }, lifecycleProvider)
//
//    }


}