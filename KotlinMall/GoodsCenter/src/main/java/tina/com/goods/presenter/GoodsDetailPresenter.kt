package tina.com.goods.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.base.utils.AppPrefsUtils
import tina.com.goods.common.GoodsConstant
import tina.com.goods.data.protocol.Goods
import tina.com.goods.presenter.view.GoodsDetailView
import tina.com.goods.service.CartService
import tina.com.goods.service.GoodsService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var userService: GoodsService

    @Inject
    lateinit var cartService: CartService

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


    /*
        加入购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice,
                goodsCount, goodsSku).execute(object : BaseSubscribler<Int>(mView) {
            override fun onNext(t: Int) {
                AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, t)
                mView.onAddCartResult(t)
            }
        }, lifecycleProvider)

    }

}