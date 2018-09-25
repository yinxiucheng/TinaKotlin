package com.tina.ordercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.order.injection.component.DaggerOrderComponent
import com.kotlin.order.injection.module.OrderModule
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.tina.base.ext.onClick
import com.tina.base.ext.setVisible
import com.tina.base.ui.activity.BaseMvpActivity
import com.tina.base.utils.YuanFenConverter
import com.tina.order.data.protocol.Order
import com.tina.ordercenter.R
import com.tina.ordercenter.event.SelectAddressEvent
import com.tina.ordercenter.presenter.OrderConfirmPresenter
import com.tina.ordercenter.presenter.view.OrderConfirView
import com.tina.provider.common.ProviderConstant
import com.tina.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity

/**
 * @author yxc
 * @date 2018/9/24
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId:Int = 0

    private lateinit var mAdapter:OrderGoodsAdapter
    private var mCurrentOrder:Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        initObserve()
        loadData()
    }


    /*
        初始化视图
     */
    private fun initView() {
        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }
        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {
                mPresenter.submitOrder(it)
            }
        }

        //订单中商品列表
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    /*
        初始化选择收货人事件监听
     */
    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe{
                    t: SelectAddressEvent ->
                    run {
                        mCurrentOrder?.let {
                            it.shipAddress = t.address
                        }
                        updateAddressView()
                    }
                }
                .registerInBus(this)

    }

    /*
        加载订单数据
     */
    private fun loadData() {
//        mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)
        mPresenter.getOrderById(mOrderId)
    }

    /*
        获取订单回调
     */
    override fun onGetOrderByIdResult(result: Order) {
        mCurrentOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"

        updateAddressView()
    }

    private fun updateAddressView() {
        mCurrentOrder?.let {
            if (it.shipAddress == null){
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            }else{
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)

                mShipNameTv.text = it.shipAddress!!.shipUserName + "  "+
                        it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent)
                .orderModule(OrderModule()).build().inject(this)

        mPresenter.mView = this
    }

}