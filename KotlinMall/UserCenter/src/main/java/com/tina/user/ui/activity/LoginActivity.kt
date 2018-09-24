package com.tina.user.ui.activity

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tina.base.common.HOME_BANNER_FOUR
import com.tina.base.common.HOME_BANNER_ONE
import com.tina.base.common.HOME_BANNER_THREE
import com.tina.base.common.HOME_BANNER_TWO
import com.tina.base.ext.enable
import com.tina.base.ext.onClick
import com.tina.base.ui.activity.BaseMvpActivity
import com.tina.base.utils.GlideUtils
import com.tina.base.widgets.BannerImageLoader
import com.tina.user.R
import com.tina.user.data.protocol.UserInfo
import com.tina.user.injection.component.DaggerUserComponent
import com.tina.user.injection.module.UserModule
import com.tina.user.presenter.LoginPresenter
import com.tina.user.presenter.view.LoginView
import com.tina.user.utils.UserPrefsUtils
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 登录
 *
 */
@Route(path = "/userCenter/login")
class LoginActivity : BaseMvpActivity<LoginPresenter>(),
        LoginView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        permission()
        injectComponent()
    }


    /*
      初始化视图
   */
    private fun initView() {
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })

        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")
            }

            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }

            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()

    }


    /**
     * 登录成功回调
     */
    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(result)
        finish()
    }

    private fun permission() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe({ permission ->
                    if (permission.granted) {
                        // 用户已经同意该权限
                        toast("用户已经同意该权限")
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        toast("用户拒绝了该权限")
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
                        toast("权限被拒绝，请在设置里面开启相应权限，若无相应权限会影响使用")
                    }
                })
    }
}
