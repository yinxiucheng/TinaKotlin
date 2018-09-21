package tina.com.goods.injection.component

import com.tina.base.injection.PerComponentScope
import com.tina.base.injection.component.ActivityComponent
import dagger.Component
import tina.com.goods.injection.module.GoodsModule
import tina.com.goods.ui.activity.GoodsActivity

/*
    商品Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(GoodsModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
}
