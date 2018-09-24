package com.tina.base.widgets

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.tina.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/*
    Banner图片加载器
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Log.d("BannerImageLoder", path.toString())
        GlideUtils.loadUrlImage(context, path.toString(), imageView)
    }
}
