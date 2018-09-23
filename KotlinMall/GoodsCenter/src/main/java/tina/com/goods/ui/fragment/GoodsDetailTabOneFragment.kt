package tina.com.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tina.base.ext.startLoading
import com.tina.base.ui.adapter.BaseRecyclerViewAdapter
import com.tina.base.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity
import tina.com.goods.R
import tina.com.goods.data.protocol.Category
import tina.com.goods.ui.activity.GoodsActivity
import tina.com.goods.ui.adapter.SecondCategoryAdapter
import tina.com.goods.ui.adapter.TopCategoryAdapter

/**
 * @author yxc
 * @date 2018/9/20
 */
class GoodsDetailTabOneFragment : BaseFragment(){

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




    /*
       加载数据
    */
    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }

    }


}