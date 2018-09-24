package tina.com.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.tina.base.ext.onClick
import com.tina.base.ui.activity.BaseActivity
import com.tina.base.utils.AppPrefsUtils
import com.tina.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import q.rorbin.badgeview.QBadgeView
import tina.com.goods.R
import tina.com.goods.common.GoodsConstant
import tina.com.goods.event.AddCartEvent
import tina.com.goods.ui.adapter.GoodsDetailVpAdapter

/**
 * @author yxc
 * @date 2018/9/20
 */
class GoodsDetailActivity : BaseActivity() {

    private lateinit var mCartBdage: QBadgeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        loadCartSize()
        initObserve()
    }

    fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED

        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)

        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mCartBdage = QBadgeView(this)
    }

    /*
      监听SKU变化及加入购物车事件
   */
    private fun initObserve() {

        Bus.observe<AddCartEvent>()
                .subscribe {
                    setCartBadge()
                }.registerInBus(this)
    }


    /*
        加载购物车数量
     */
    private fun loadCartSize() {
        setCartBadge()
    }

    private fun setCartBadge() {
        mCartBdage.badgeGravity = Gravity.END or Gravity.TOP
        mCartBdage.setGravityOffset(22f, -2f, true)
        mCartBdage.setBadgeTextSize(6f, true)
        mCartBdage.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }



    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}