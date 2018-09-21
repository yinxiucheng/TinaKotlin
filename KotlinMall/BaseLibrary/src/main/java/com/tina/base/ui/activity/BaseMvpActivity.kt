package com.tina.base.ui.activity

import android.os.Bundle
import com.tina.base.common.BaseApplication
import com.tina.base.injection.component.ActivityComponent
import com.tina.base.injection.component.DaggerActivityComponent
import com.tina.base.injection.module.ActivityModule
import com.tina.base.injection.module.LifecycleProviderModule
import com.tina.base.presenter.BasePresenter
import com.tina.base.presenter.view.BaseView
import com.tina.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
abstract class BaseMvpActivity<T:BasePresenter<*>>: BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter:T

    lateinit var mActivityComponent:ActivityComponent

    private lateinit var mLoadingDialog:ProgressLoading

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(string: String) {
        toast(string)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()

        mLoadingDialog = ProgressLoading.create(this)
    }

    /*
      Dagger注册
   */
    protected abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

}