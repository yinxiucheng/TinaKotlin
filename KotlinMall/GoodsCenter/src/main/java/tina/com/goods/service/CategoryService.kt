package tina.com.goods.service

import rx.Observable
import tina.com.goods.data.protocol.Category

/**
 * @author yxc
 * @date 2018/9/15
 */
interface CategoryService {

    fun getCategory(parentId: Int): Observable<MutableList<Category>?>
}
