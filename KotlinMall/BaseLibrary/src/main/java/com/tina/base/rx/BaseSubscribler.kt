package com.tina.base.rx

import rx.Subscriber

/**
 * @author yxc
 * @date 2018/9/15
 *
 */
open class BaseSubscribler<T>: Subscriber<T>() {

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }


}