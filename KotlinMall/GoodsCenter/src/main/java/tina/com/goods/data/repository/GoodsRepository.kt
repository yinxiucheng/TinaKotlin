package tina.com.goods.data.repository

import com.kotlin.goods.data.protocol.GetGoodsDetailReq
import com.kotlin.goods.data.protocol.GetGoodsListByKeywordReq
import com.kotlin.goods.data.protocol.GetGoodsListReq
import com.tina.base.data.net.RetrofitFactory
import com.tina.base.data.net.protocol.BaseResp
import rx.Observable
import tina.com.goods.data.api.GoodsApi
import tina.com.goods.data.protocol.Goods
import javax.inject.Inject

/*
    商品数据层
 */
class GoodsRepository @Inject constructor() {

    /*
        根据分类搜索商品
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsList(GetGoodsListReq(categoryId, pageNo))
    }

    /*
        根据关键字搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsListByKeyword(GetGoodsListByKeywordReq(keyword, pageNo))
    }

    /*
        商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsDetail(GetGoodsDetailReq(goodsId))
    }
}
