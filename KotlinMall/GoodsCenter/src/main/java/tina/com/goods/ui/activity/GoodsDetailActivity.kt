package tina.com.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.tina.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_goods_detail.*
import tina.com.goods.R
import tina.com.goods.ui.adapter.GoodsDetailVpAdapter

/**
 * @author yxc
 * @date 2018/9/20
 */
class GoodsDetailActivity: BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    fun initView(){
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED

        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)

        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
    }


}