package tina.com.goods.injection.module

import dagger.Module
import dagger.Provides
import tina.com.goods.service.GoodsService
import tina.com.goods.service.impl.GoodsServiceImpl

/*
    商品Module
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodservice(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }

}
