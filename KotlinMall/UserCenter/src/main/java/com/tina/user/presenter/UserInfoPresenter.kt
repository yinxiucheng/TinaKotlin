package com.tina.user.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.user.presenter.view.RegisterView
import com.tina.user.presenter.view.UserInfoView
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
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    @field:[Named("service")]
    lateinit var userService: UserService


}