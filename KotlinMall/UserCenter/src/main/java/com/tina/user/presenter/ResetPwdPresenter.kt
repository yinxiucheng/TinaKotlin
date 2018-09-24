package com.tina.user.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.user.presenter.view.ResetPwdView
import com.tina.user.service.UserService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String) {
        /*
            业务逻辑
         */
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile, pwd)
                .execute(object : BaseSubscribler<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t){
                            mView.onResetPwdResult("验证成功")
                        }
                    }
                }, lifecycleProvider)
    }

}