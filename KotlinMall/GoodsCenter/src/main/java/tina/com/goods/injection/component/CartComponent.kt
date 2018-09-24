package tina.com.goods.injection.component

import com.tina.base.injection.PerComponentScope
import com.tina.base.injection.component.ActivityComponent
import dagger.Component
import tina.com.goods.injection.module.CartModule
import tina.com.goods.injection.module.GoodsModule
import tina.com.goods.ui.activity.GoodsActivity
import tina.com.goods.ui.fragment.CartFragment
import tina.com.goods.ui.fragment.GoodsDetailTabOneFragment
import tina.com.goods.ui.fragment.GoodsDetailTabTwoFragment

/*
    商品Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment: CartFragment)
}
