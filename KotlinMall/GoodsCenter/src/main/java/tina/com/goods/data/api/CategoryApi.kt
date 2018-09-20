package tina.com.goods.data.api

import com.tina.base.data.net.protocol.BaseResp
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable
import tina.com.goods.data.protocol.Category
import tina.com.goods.data.protocol.GetCategoryReq

/**
 * @author yxc
 * @date 2018/9/20
 *
 */
interface CategoryApi {

    /**
     * 获取商品分类列表
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>


}