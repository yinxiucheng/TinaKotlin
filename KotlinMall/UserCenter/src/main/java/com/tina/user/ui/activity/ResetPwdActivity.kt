package com.tina.user.ui.activity

import android.os.Bundle
import com.tina.base.ext.enable
import com.tina.base.ext.onClick
import com.tina.base.ui.activity.BaseMvpActivity
import com.tina.user.R
import com.tina.user.injection.component.DaggerUserComponent
import com.tina.user.injection.module.UserModule
import com.tina.user.presenter.ResetPwdPresenter
import com.tina.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.toast

/**
 * 重置密码
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
        injectComponent()
    }

    /*
      初始化视图
   */
    private fun initView() {

        mConfirmBtn.enable(mPwdEt, { isBtnEnable() })
        mConfirmBtn.enable(mPwdConfirmEt, { isBtnEnable() })

        mConfirmBtn.onClick{
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                toast("密码不一致")
                return@onClick
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onResetPwdResult(result: String) {
        toast("重置密码成功")
    }


    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }


}
