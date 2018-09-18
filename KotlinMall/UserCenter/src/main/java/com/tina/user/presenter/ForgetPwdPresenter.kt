package com.tina.user.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.user.presenter.view.ForgetPwdView
import com.tina.user.service.UserService
import javax.inject.Inject
import javax.inject.Named

/**
 * @author yxc
 * @date 2018/9/15
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    @field:[Named("service")]
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        /*
            业务逻辑
         */
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.forgetPwd(mobile, verifyCode)
                .execute(object : BaseSubscribler<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t){
                            mView.onForgetPwdResult("验证成功")
                        }
                    }
                }, lifecycleProvider)
    }

}