package tina.com.goods.ui.activity

import android.os.Bundle
import com.tina.base.ui.activity.BaseActivity
import tina.com.goods.R
import tina.com.goods.ui.fragment.CartFragment

/**
 * @author yxc
 * @date 2018/9/20
 */
class CartActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)

    }
}