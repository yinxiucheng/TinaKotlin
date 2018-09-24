package tina.com.goods.presenter.view

import com.tina.base.presenter.view.BaseView
import tina.com.goods.data.protocol.Goods

/**
 * @author yxc
 * @date 2018/9/15
 */
interface GoodsDetailView:BaseView {

    fun onGetGoodsDetailResult(result: Goods)
}