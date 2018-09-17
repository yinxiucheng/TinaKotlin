package com.tina.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tina.base.common.BaseApplication
import com.tina.base.injection.component.ActivityComponent
import com.tina.base.injection.component.DaggerActivityComponent
import com.tina.base.injection.module.ActivityModule
import com.tina.base.injection.module.LifecycleProviderModule
import com.tina.base.presenter.BasePresenter
import com.tina.base.presenter.view.BaseView
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/17
 */
open abstract class BaseMvpFragment<T:BasePresenter<*>>:BaseFragment(), BaseView {
    @Inject
    lateinit var mPresenter:T

    lateinit var mActivityComponent: ActivityComponent

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(erroStr: String) {
        toast(erroStr)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initActivityInjection()
        injectComponent()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /*
       Dagger注册
    */
    protected abstract fun injectComponent()

    /*
        初始化Activity级别Component
     */
    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }





}