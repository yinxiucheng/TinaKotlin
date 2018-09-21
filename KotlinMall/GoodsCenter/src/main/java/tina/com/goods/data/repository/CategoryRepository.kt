package tina.com.goods.data.repository

import com.tina.base.data.net.RetrofitFactory
import com.tina.base.data.net.protocol.BaseResp
import com.tina.goods.data.protocol.GetCategoryReq
import rx.Observable
import tina.com.goods.data.api.CategoryApi
import tina.com.goods.data.protocol.Category
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/16
 */
class CategoryRepository @Inject constructor() {

    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> {

        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }





}