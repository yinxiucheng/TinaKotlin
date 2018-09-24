package com.tina.user.data.api

import com.tina.base.data.net.protocol.BaseResp
import com.tina.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * @author yxc
 * @date 2018/9/16
 */
interface UploadApi {
    /*
      用户注册
   */
    @POST("common/getUploadToken")
    fun getUploadToken():Observable<BaseResp<String>>


}