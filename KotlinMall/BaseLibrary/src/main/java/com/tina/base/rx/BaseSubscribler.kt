package com.tina.base.rx

import com.tina.base.presenter.view.BaseView
import rx.Subscriber

/**
 * @author yxc
 * @date 2018/9/15
 *
 */
open class BaseSubscribler<T>(val baseView:BaseView): Subscriber<T>() {

    override fun onNext(t: T) {
        baseView.hideLoading()
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if (e is BaseException){
            baseView.onError(e.msg)
        }
    }

}