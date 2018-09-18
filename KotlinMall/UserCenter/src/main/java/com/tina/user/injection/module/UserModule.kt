package com.tina.user.injection.module

import com.tina.user.service.UserService
import com.tina.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @author yxc
 * @date 2018/9/16
 */

@Module
class UserModule{

    @Provides
    fun providesUserService(service:UserServiceImpl):UserService{
        return  service;
    }
}