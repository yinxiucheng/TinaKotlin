package tina.com.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.tina.base.ui.fragment.BaseMvpFragment
import com.tina.base.utils.YuanFenConverter
import com.tina.base.widgets.BannerImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import tina.com.goods.R
import tina.com.goods.common.GoodsConstant
import tina.com.goods.data.protocol.Goods
import tina.com.goods.event.GoodsDetailImageEvent
import tina.com.goods.injection.component.DaggerGoodsComponent
import tina.com.goods.injection.module.GoodsModule
import tina.com.goods.presenter.GoodsDetailPresenter
import tina.com.goods.presenter.view.GoodsDetailView

/**
 * @author yxc
 * @date 2018/9/20
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }



    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent)
                .goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }


    /*
       加载数据
    */
    private fun loadData(parentId: Int = 0) {
        mPresenter.getGoodsDetail(parentId)
    }


    override fun onGetGoodsDetailResult(result: Goods) {
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))
    }

}