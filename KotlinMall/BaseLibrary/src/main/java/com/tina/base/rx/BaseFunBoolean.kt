package com.tina.base.rx

import com.tina.base.common.ResultCode
import com.tina.base.data.net.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * @author yxc
 * @date 2018/9/17
 */
class BaseFunBoolean<T>:Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean>{
        if (t.status != ResultCode.SUCCESS) {
            return rx.Observable.error(BaseException(t.status, t.message))
        }
        return rx.Observable.just(true)
    }
}