package tina.com.goods.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.tina.base.ext.onClick
import com.tina.base.ui.activity.BaseActivity
import com.tina.base.ui.fragment.BaseMvpFragment
import com.tina.base.utils.YuanFenConverter
import com.tina.base.widgets.BannerImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import tina.com.goods.R
import tina.com.goods.common.GoodsConstant
import tina.com.goods.data.protocol.Goods
import tina.com.goods.event.AddCartEvent
import tina.com.goods.event.GoodsDetailImageEvent
import tina.com.goods.event.SkuChangedEvent
import tina.com.goods.event.UpdateCartEvent
import tina.com.goods.injection.component.DaggerGoodsComponent
import tina.com.goods.injection.module.GoodsModule
import tina.com.goods.presenter.GoodsDetailPresenter
import tina.com.goods.presenter.view.GoodsDetailView
import tina.com.goods.ui.activity.GoodsDetailActivity
import tina.com.goods.widget.GoodsSkuPopView

/**
 * @author yxc
 * @date 2018/9/20
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {



    private lateinit var mSkuPop: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods: Goods? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }


    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)


        //sku弹层
        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView
                    , Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                    0, 0
            )
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent)
                .goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }


    /*
     初始化缩放动画
  */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    /*
        初始化sku弹层
     */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(act)
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }


    /*
       监听SKU变化及加入购物车事件
    */
    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
                }.registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    /*
       加载数据
    */
    private fun loadData() {
        mPresenter.getGoodsDetail(act.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    private fun addCart() {
        mCurGoods?.let {
            mPresenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku())
        }
    }


    override fun onGetGoodsDetailResult(result: Goods) {

        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }

    /*
        加载SKU数据
     */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }

    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartEvent())
        toast("Cart ---------- $result")
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}