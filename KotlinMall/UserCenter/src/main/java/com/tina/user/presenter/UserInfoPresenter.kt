package com.tina.user.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import com.tina.user.data.protocol.UserInfo
import com.tina.user.presenter.view.UserInfoView
import com.tina.user.service.UploadService
import com.tina.user.service.UserService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken(){
        if (!checkNetWork()){
            return
        }

        mView.showLoading()
        uploadService.getUploadToken().execute(object :BaseSubscribler<String>(mView){
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        }, lifecycleProvider)
    }


    fun editUser(userIcon:String, userName:String, userGender:String, userSign:String){
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign).execute(object :BaseSubscribler<UserInfo>(mView){
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        }, lifecycleProvider)
    }
}