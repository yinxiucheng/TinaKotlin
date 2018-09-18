package com.tina.user.service

import com.tina.user.data.protocol.UserInfo
import rx.Observable

/**
 * @author yxc
 * @date 2018/9/15
 */
interface UploadService {

    fun getUploadToken(): Observable<String>


}
