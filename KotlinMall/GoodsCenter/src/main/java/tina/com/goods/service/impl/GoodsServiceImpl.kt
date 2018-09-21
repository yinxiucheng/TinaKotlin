package tina.com.goods.service.impl

import com.tina.base.ext.convert
import rx.Observable
import tina.com.goods.data.protocol.Category
import tina.com.goods.data.protocol.Goods
import tina.com.goods.data.repository.CategoryRepository
import tina.com.goods.data.repository.GoodsRepository
import tina.com.goods.service.CategoryService
import tina.com.goods.service.GoodsService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class GoodsServiceImpl @Inject constructor(): GoodsService {

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId, pageNo).convert()
    }

    @Inject
    lateinit var repository: GoodsRepository

}