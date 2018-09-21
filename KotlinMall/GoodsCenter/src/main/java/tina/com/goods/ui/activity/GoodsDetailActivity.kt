package tina.com.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.kennyc.view.MultiStateView
import com.tina.base.ext.startLoading
import com.tina.base.ui.activity.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_goods.*
import tina.com.goods.R
import tina.com.goods.data.protocol.Goods
import tina.com.goods.injection.component.DaggerGoodsComponent
import tina.com.goods.injection.module.GoodsModule
import tina.com.goods.presenter.GoodsListPresenter
import tina.com.goods.presenter.view.GoodsListView
import tina.com.goods.ui.adapter.GoodsAdapter

/**
 * @author yxc
 * @date 2018/9/20
 */
class GoodsDetailActivity: BaseMvpActivity<GoodsListPresenter>(), GoodsListView {

    private lateinit var mGoodsAdapter: GoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        loadData()
    }

    fun initView(){
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter


    }

    private fun loadData(){
        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra("categoryId", 1), 1)
    }

    override fun onGetGoodsResult(result: MutableList<Goods>?) {
        if (result != null && result.size > 0) {
            mGoodsAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            //没有数据
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun injectComponent() {
        mPresenter.mView = this
    }

}