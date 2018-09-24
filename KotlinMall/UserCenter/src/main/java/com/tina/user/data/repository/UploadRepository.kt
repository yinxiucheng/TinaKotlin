package com.tina.user.data.repository

import com.tina.base.data.net.RetrofitFactory
import com.tina.base.data.net.protocol.BaseResp
import com.tina.user.data.api.UploadApi
import com.tina.user.data.api.UserApi
import com.tina.user.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/16
 */
class UploadRepository @Inject constructor() {

    fun getUploadToken():Observable<BaseResp<String>>{

        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }


}