package com.tina.user.ui.activity

import android.os.Bundle
import com.kotlin.user.R
import com.tina.base.ui.BaseMvpActivity
import com.tina.user.presenter.RegisterPresenter
import com.tina.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()

        mPresenter = RegisterPresenter()
        mPresenter.mView = this

        mRegisterBtn.isEnabled = true
        mRegisterBtn.setOnClickListener {

            mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
        }
    }

    /*
       初始化视图
    */
    private fun initView() {

//        mRegisterBtn.enable(mMobileEt,{isBtnEnable()})
//        mRegisterBtn.enable(mVerifyCodeEt,{isBtnEnable()})
//        mRegisterBtn.enable(mPwdEt,{isBtnEnable()})
//        mRegisterBtn.enable(mPwdConfirmEt,{isBtnEnable()})
//
//        mVerifyCodeBtn.onClick(this)
//        mRegisterBtn.onClick(this)
    }
}
