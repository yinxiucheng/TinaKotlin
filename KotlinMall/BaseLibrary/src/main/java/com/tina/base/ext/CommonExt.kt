package com.tina.base.ext

import com.tina.base.rx.BaseSubscribler
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author yxc
 * @date 2018/9/15
 */

fun <T> Observable<T>.execute(subscriber: BaseSubscribler<T>){

    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)

}