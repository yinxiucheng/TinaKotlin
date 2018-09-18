package com.tina.user.injection.component

import com.tina.base.injection.PreComponentScope
import com.tina.base.injection.component.ActivityComponent
import com.tina.user.data.protocol.UserInfo
import com.tina.user.injection.module.UserModule
import com.tina.user.ui.activity.*
import dagger.Component

/**
 * @author yxc
 * @date 2018/9/16
 */
@PreComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(activity:RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}