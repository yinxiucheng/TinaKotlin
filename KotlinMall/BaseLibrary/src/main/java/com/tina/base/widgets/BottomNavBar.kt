package com.tina.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.tina.base.R

/**
 * @author yxc
 * @date 2018/9/18
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    //购物车Tab 标签
    private val mCartBadge: TextBadgeItem
    //消息Tab 标签
    private val mMsgBadge: ShapeBadgeItem

    init {
        //首页
        val homeItem = BottomNavigationItem(R.mipmap.btn_nav_home_press, resources.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.mipmap.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //分类
        val categoryItem = BottomNavigationItem(R.mipmap.btn_nav_category_press, resources.getString(R.string.nav_bar_category))
                .setInactiveIconResource(R.mipmap.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //购物车
        val cartItem = BottomNavigationItem(R.mipmap.btn_nav_cart_press, resources.getString(R.string.nav_bar_cart))
                .setInactiveIconResource(R.mipmap.btn_nav_cart_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        mCartBadge = TextBadgeItem()
        cartItem.setBadgeItem(mCartBadge)

        //消息
        val msgItem = BottomNavigationItem(R.mipmap.btn_nav_msg_press, resources.getString(R.string.nav_bar_msg))
                .setInactiveIconResource(R.mipmap.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        mMsgBadge = ShapeBadgeItem()
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)
        msgItem.setBadgeItem(mMsgBadge)

        //我的
        val userItem = BottomNavigationItem(R.mipmap.btn_nav_user_press, resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.mipmap.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //设置底部导航模式及样式
        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)
        //添加Tab
        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(msgItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }

    fun checkCartBadge(count: Int) {
        if (count == 0) {
            mCartBadge.hide()
        } else {
            mCartBadge.show()
            mCartBadge.setText("${count}")
        }
    }

    fun checkMsgBadge(visible: Boolean) {
        if (visible) {
            mMsgBadge.show()
        } else {
            mMsgBadge.hide()
        }
    }



}