package com.tina.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.tina.base.data.net.protocol.BaseResp
import com.tina.base.rx.BaseFunBoolean
import com.tina.base.rx.BaseFunc
import com.tina.base.rx.BaseSubscribler
import com.tina.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author yxc
 * @date 2018/9/15
 */

fun <T> Observable<T>.execute(subscriber: BaseSubscribler<T>, lifecycleProvider: LifecycleProvider<*>){

    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)

}


fun View.onClick(listener: View.OnClickListener){
    this.setOnClickListener(listener)
}


//扩展lamada表达式

fun View.onClick(method: () -> Unit){
    this.setOnClickListener { method() }
}

/*
    扩展Boolean类型数据转换
 */
fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFunBoolean())
}

/*
    扩展数据转换
 */
fun <T> Observable<BaseResp<T>>.convert():Observable<T>{
    return this.flatMap(BaseFunc())
}


fun Button.enable(et: EditText, method: () -> Boolean){
    val btn = this
    et.addTextChangedListener(object :DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}