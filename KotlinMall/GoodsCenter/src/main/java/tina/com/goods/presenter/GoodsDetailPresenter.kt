package tina.com.goods.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import tina.com.goods.data.protocol.Goods
import tina.com.goods.presenter.view.GoodsDetailView
import tina.com.goods.service.GoodsService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var userService: GoodsService

    fun getGoodsDetail(goodsId: Int) {
        /*
            业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.getGoodsDetail(goodsId)
                .execute(object : BaseSubscribler<Goods>(mView) {
                    override fun onNext(t: Goods) {
                        mView.onGetGoodsDetailResult(t)
                    }
                }, lifecycleProvider)
    }

}