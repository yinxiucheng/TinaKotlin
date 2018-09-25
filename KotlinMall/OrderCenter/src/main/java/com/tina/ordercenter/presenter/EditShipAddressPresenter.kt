package com.tina.ordercenter.presenter

import com.kotlin.order.presenter.view.EditShipAddressView
import com.kotlin.order.service.ShipAddressService
import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.order.data.protocol.ShipAddress
import javax.inject.Inject

/*
    编辑收货人信息Presenter
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService


    /*
        添加收货人信息
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName,shipUserMobile,shipAddress).execute(object : BaseSubscribler<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                    mView.onAddShipAddressResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        修改收货人信息
     */
    fun editShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).execute(object : BaseSubscribler<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onEditShipAddressResult(t)
            }
        }, lifecycleProvider)

    }


}
