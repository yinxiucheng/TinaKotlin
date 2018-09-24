package tina.com.goods.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import tina.com.goods.data.protocol.Category
import tina.com.goods.data.protocol.Goods
import tina.com.goods.presenter.view.CategoryView
import tina.com.goods.presenter.view.GoodsListView
import tina.com.goods.service.CategoryService
import tina.com.goods.service.GoodsService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var userService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        /*
            业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.getGoodsList(categoryId, pageNo)
                .execute(object : BaseSubscribler<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        mView.onGetGoodsResult(t)
                    }
                }, lifecycleProvider)
    }

    fun getGoodsListByKeyWord(keyword: String, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.getGoodsListByKeyWord(keyword, pageNo)
                .execute(object : BaseSubscribler<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        mView.onGetGoodsResult(t)
                    }
                }, lifecycleProvider)
    }

}