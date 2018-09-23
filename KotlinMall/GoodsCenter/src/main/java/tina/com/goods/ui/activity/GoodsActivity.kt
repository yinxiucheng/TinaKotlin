package tina.com.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.tina.base.ext.startLoading
import com.tina.base.ui.activity.BaseMvpActivity
import com.tina.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity
import tina.com.goods.R
import tina.com.goods.common.GoodsConstant
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
class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView, BGARefreshLayout.BGARefreshLayoutDelegate {


    private lateinit var mGoodsAdapter: GoodsAdapter

    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1

    private var mData: MutableList<Goods>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout()
        loadData()
    }

    fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter

        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods>{
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>()
            }
        })
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        var viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyWord(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), mCurrentPage)
        } else {
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(intent.getIntExtra("categoryId", 1), mCurrentPage)
        }
    }

    override fun onGetGoodsResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1) {
                mGoodsAdapter.setData(result)
            } else {
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            //没有数据
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent)
                .goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            return true
        } else {
            return false
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

}