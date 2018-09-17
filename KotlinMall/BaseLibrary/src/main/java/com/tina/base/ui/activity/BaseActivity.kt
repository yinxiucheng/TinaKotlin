package com.tina.base.ui.activity

import android.os.Bundle
import com.tina.base.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * @author yxc
 * @date 2018/9/15
 */
open class BaseActivity: RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addAcitivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishAllActivity()
    }


}