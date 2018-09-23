package tina.com.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.tina.base.ext.loadUrl
import com.tina.base.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*
import tina.com.goods.R
import tina.com.goods.event.GoodsDetailImageEvent

/**
 * @author yxc
 * @date 2018/9/20
 */
class GoodsDetailTabTwoFragment : BaseFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_goods_detail_tab_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObsever()
    }

    private fun initObsever(){
        Bus.observe<GoodsDetailImageEvent>().subscribe{
            kotlin.run {
                mGoodsDetailOneIv.loadUrl(it.imgOne)
                mGoodsDetailTwoIv.loadUrl(it.imgTwo)
            }
        }.registerInBus(this)
    }


    private fun initView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }


}