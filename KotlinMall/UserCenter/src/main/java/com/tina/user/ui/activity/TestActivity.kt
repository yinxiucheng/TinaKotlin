package com.tina.user.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast

/**
 * @author yxc
 * @date 2018/9/15
 */
class TestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast("${intent.getIntExtra("id", -1)}")

    }
}