package com.tina.user.presenter.view

import com.tina.base.presenter.view.BaseView
import com.tina.user.data.protocol.UserInfo

/**
 * @author yxc
 * @date 2018/9/15
 */
interface LoginView:BaseView {

    fun onLoginResult(result: UserInfo)
}