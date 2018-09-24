package tina.com.goods.presenter.view

import com.tina.base.presenter.view.BaseView
import tina.com.goods.data.protocol.Category

/**
 * @author yxc
 * @date 2018/9/15
 */
interface CategoryView:BaseView {

    fun onGetCategoryResult(result: MutableList<Category>?)
}