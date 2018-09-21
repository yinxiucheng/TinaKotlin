package tina.com.goods.presenter.view

import com.tina.base.presenter.view.BaseView
import tina.com.goods.data.protocol.Category
import tina.com.goods.data.protocol.Goods

/**
 * @author yxc
 * @date 2018/9/15
 */
interface GoodsListView:BaseView {

    fun onGetGoodsResult(result: MutableList<Goods>?)
}