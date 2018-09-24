package tina.com.goods.injection.component

import com.tina.base.injection.PerComponentScope
import com.tina.base.injection.component.ActivityComponent
import dagger.Component
import tina.com.goods.injection.module.CategoryModule
import tina.com.goods.ui.fragment.CategoryFragment

/**
 * @author yxc
 * @date 2018/9/16
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}

