package com.tina.user.injection.module

import com.tina.user.service.UserService
import com.tina.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author yxc
 * @date 2018/9/16
 */

@Module
class UserModule{

    @Provides
    @Named("service")
    fun providesUserService(service:UserServiceImpl):UserService{
        return  service;
    }


    @Provides
    @Named("service2")
    fun providesUserService2(service:UserServiceImpl):UserService{
        return  service;
    }

}