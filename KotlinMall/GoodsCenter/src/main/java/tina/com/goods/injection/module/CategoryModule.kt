package tina.com.goods.injection.module

import dagger.Module
import dagger.Provides
import tina.com.goods.service.CategoryService
import tina.com.goods.service.impl.CategoryServiceImpl

/**
 * @author yxc
 * @date 2018/9/16
 */

@Module
class CategoryModule{

    @Provides
    fun providesUploadService(service: CategoryServiceImpl): CategoryService{
        return service;
    }
}