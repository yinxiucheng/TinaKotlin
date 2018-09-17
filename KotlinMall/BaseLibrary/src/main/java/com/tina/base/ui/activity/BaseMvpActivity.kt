package com.tina.base.ui.activity

import android.os.Bundle
import com.tina.base.common.BaseApplication
import com.tina.base.injection.component.ActivityComponent
import com.tina.base.injection.component.DaggerActivityComponent
import com.tina.base.injection.module.ActivityModule
import com.tina.base.injection.module.LifecycleProviderModule
import com.tina.base.presenter.BasePresenter
import com.tina.base.presenter.view.BaseView
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
open abstract class BaseMvpActivity<T:BasePresenter<*>>: BaseActivity(), BaseView {

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {

    }

    @Inject
    lateinit var mPresenter:T

    lateinit var mActivityComponent:ActivityComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()
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