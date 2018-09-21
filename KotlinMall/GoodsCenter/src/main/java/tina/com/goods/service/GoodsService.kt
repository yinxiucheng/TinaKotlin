package tina.com.goods.service

import rx.Observable
import tina.com.goods.data.protocol.Category
import tina.com.goods.data.protocol.Goods

/**
 * @author yxc
 * @date 2018/9/15
 */
interface GoodsService {

    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
}
