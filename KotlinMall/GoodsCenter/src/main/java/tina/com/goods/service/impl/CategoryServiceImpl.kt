package tina.com.goods.service.impl

import com.tina.base.ext.convert
import rx.Observable
import tina.com.goods.data.protocol.Category
import tina.com.goods.data.repository.CategoryRepository
import tina.com.goods.service.CategoryService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class CategoryServiceImpl @Inject constructor(): CategoryService {

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }

    @Inject
    lateinit var repository: CategoryRepository

}