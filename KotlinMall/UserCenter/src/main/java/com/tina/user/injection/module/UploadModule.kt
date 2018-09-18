package com.tina.user.injection.module

import com.tina.user.service.UploadService
import com.tina.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @author yxc
 * @date 2018/9/16
 */

@Module
class UploadModule{

    @Provides
    fun providesUploadService(service:UploadServiceImpl):UploadService{
        return service;
    }
}