package com.tina.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.DateUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import com.tina.base.common.BaseConstant
import com.tina.base.ext.onClick
import com.tina.base.ui.activity.BaseMvpActivity
import com.tina.base.utils.GlideUtils
import com.tina.user.R
import com.tina.user.injection.component.DaggerUserComponent
import com.tina.user.injection.module.UserModule
import com.tina.user.presenter.UserInfoPresenter
import com.tina.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import org.json.JSONObject
import java.io.File

/**
 * 用户信息
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener {

    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    private val mUPloadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFile: String? = null

    private var mRemoteFile: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this, this)
        initView()
        injectComponent()

        mTakePhoto.onCreate(savedInstanceState)
    }

    /*
      初始化视图
   */
    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }
    }


    /*
       弹出选择框，默认实现
       可根据实际情况，自行修改
    */
    protected fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            when (position) {
                0 -> {
                    createTempFile()
                    mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                }
                1 -> mTakePhoto.onPickFromGallery()
            }
        }

        ).show()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto", result?.image?.compressPath)

        mLocalFile = result?.image?.compressPath

        mPresenter.getUploadToken()
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto", msg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }

        this.mTempFile = File(filesDir, tempFileName)
    }


    override fun onGetUploadTokenResult(resutl: String) {
        mUPloadManager.put(mLocalFile, null, resutl, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFile = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")

                Log.d("test", mRemoteFile)

                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFile!!, mUserIconIv)
            }
        }, null)
    }


}
