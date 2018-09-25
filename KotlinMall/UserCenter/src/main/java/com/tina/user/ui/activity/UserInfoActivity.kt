package com.tina.user.ui.activity

import android.os.Bundle
import android.util.Log
import com.jph.takephoto.model.TResult
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import com.tina.base.common.BaseConstant
import com.tina.base.ext.onClick
import com.tina.base.ui.activity.BaseTakePhotoActivity
import com.tina.base.utils.AppPrefsUtils
import com.tina.base.utils.GlideUtils
import com.tina.provider.common.ProviderConstant
import com.tina.user.R
import com.tina.user.data.protocol.UserInfo
import com.tina.user.injection.component.DaggerUserComponent
import com.tina.user.injection.module.UserModule
import com.tina.user.presenter.UserInfoPresenter
import com.tina.user.presenter.view.UserInfoView
import com.tina.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject

/**
 * 用户信息
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView{

    private val mUPloadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFileUrl: String? = null

    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        injectComponent()
        initView()
        initData()
    }

    private fun initData() {

        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)
    }

    /*
      初始化视图
   */
    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!,
                    mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString() ?: "")
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun takeSuccess(result: TResult?) {
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }


    override fun onGetUploadTokenResult(resutl: String) {
        mUPloadManager.put(mLocalFileUrl, null, resutl, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")

                Log.d("test", mRemoteFileUrl)

                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
            }
        }, null)
    }

    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }
}
