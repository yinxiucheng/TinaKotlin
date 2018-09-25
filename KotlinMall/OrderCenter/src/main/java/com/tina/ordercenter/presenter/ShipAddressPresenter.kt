package com.tina.ordercenter.presenter

import com.kotlin.order.presenter.view.ShipAddressView
import com.kotlin.order.service.ShipAddressService
import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.order.data.protocol.ShipAddress
import javax.inject.Inject

/*
    收货人列表Presenter
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /*
        获取收货人列表
     */
    fun getShipAddressList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.getShipAddressList().execute(object : BaseSubscribler<MutableList<ShipAddress>?>(mView) {
            override fun onNext(t: MutableList<ShipAddress>?) {
                mView.onGetShipAddressResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        设置默认收货人信息
     */
    fun setDefaultShipAddress(address:ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).execute(object : BaseSubscribler<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetDefaultResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        删除收货人信息
     */
    fun deleteShipAddress(id:Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.deleteShipAddress(id).execute(object : BaseSubscribler<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteResult(t)
            }
        }, lifecycleProvider)

    }

}
