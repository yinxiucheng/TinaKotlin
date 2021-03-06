package com.tina.user.presenter.view

import com.tina.base.presenter.view.BaseView
import com.tina.user.data.protocol.UserInfo

/**
 * @author yxc
 * @date 2018/9/15
 */
interface UserInfoView:BaseView {

    fun onGetUploadTokenResult(result:String)

    fun onEditUserResult(result:UserInfo)

}