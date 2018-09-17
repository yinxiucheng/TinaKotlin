package com.tina.user.ui.activity

import android.os.Bundle
import com.kotlin.user.R
import com.tina.base.ext.onClick
import com.tina.base.ui.activity.BaseMvpActivity
import com.tina.user.injection.component.DaggerUserComponent
import com.tina.user.injection.module.UserModule
import com.tina.user.presenter.RegisterPresenter
import com.tina.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()

        injectComponent()

        mRegisterBtn.isEnabled = true

        mRegisterBtn.onClick {
            mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
        }

        mVerifyCodeBtn.onClick {
            mPresenter.register2(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
        }
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
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
