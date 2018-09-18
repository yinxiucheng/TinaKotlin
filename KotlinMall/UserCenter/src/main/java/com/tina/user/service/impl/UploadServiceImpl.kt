package com.tina.user.service.impl

import com.tina.base.ext.convert
import com.tina.user.data.repository.UploadRepository
import com.tina.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class UploadServiceImpl @Inject constructor(): UploadService {

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }

    @Inject
    lateinit var repository: UploadRepository

}