package tina.com.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.tina.base.ext.setVisible
import com.tina.base.ext.startLoading
import com.tina.base.ui.adapter.BaseRecyclerViewAdapter
import com.tina.base.ui.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity
import tina.com.goods.R
import tina.com.goods.data.protocol.Category
import tina.com.goods.injection.component.DaggerCategoryComponent
import tina.com.goods.injection.module.CategoryModule
import tina.com.goods.presenter.CategoryPresenter
import tina.com.goods.presenter.view.CategoryView
import tina.com.goods.ui.activity.GoodsActivity
import tina.com.goods.ui.adapter.SecondCategoryAdapter
import tina.com.goods.ui.adapter.TopCategoryAdapter

/**
 * @author yxc
 * @date 2018/9/20
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    lateinit var topAdapter: TopCategoryAdapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context)
        mTopCategoryRv.adapter = topAdapter
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter
        .OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()

                loadData(item.id)
            }
        })


        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsActivity>("categoryId" to item.id)
            }
        })
    }


    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent)
                .categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }


    /*
       加载数据
    */
    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }
        mPresenter.getCategory(parentId)

    }

    /*
        获取商品分类 回调
     */
    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}