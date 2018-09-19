package com.tina.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.tina.base.R
import org.jetbrains.anko.find

/**
 * @author yxc
 * @date 2018/9/17
 */
class ProgressLoading private constructor(context: Context, theme: Int) : Dialog(context, theme) {
    companion object {
        private lateinit var mDialog: ProgressLoading

        lateinit var animationDrawable: AnimationDrawable

        fun create(context: Context): ProgressLoading{
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f

            mDialog.window.attributes = lp
            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
            animationDrawable = loadingView.background as AnimationDrawable

            return mDialog
        }
    }

    fun showLoading(){
        super.show()
        animationDrawable.start()
    }

    fun hideLoading(){
        super.dismiss()
        animationDrawable.stop()
    }

}