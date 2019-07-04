package com.whz.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.whz.base.R

/**
 *Created by whz  on 2019-06-27
 */
class BottomNavBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

//    private val mTextBadge: TextBadgeItem
//    private val mShapeBadge: ShapeBadgeItem
//
//    init {
//        val homeItem =
//            BottomNavigationItem(R.drawable.tab_ic_home_selected, resources.getString(R.string.home))
//                .setInactiveIconResource(R.drawable.tab_ic_home_default)
//                .setActiveColorResource(R.color.mainColor)
//                .setInActiveColorResource(R.color.black)
//        val foundItem =
//            BottomNavigationItem(R.drawable.tab_ic_explore_selected, resources.getString(R.string.exploration))
//                .setInactiveIconResource(R.drawable.tab_ic_explore_default)
//                .setActiveColorResource(R.color.mainColor)
//                .setInActiveColorResource(R.color.black)
//        val mainItem =
//            BottomNavigationItem(R.drawable.main_main, resources.getString(R.string.my_buy))
//                .setInactiveIconResource(R.drawable.main_main)
//                .setActiveColorResource(R.color.mainColor)
//                .setInActiveColorResource(R.color.black)
//
//        val shopItem =
//            BottomNavigationItem(R.drawable.tab_ic_shop_selected, resources.getString(R.string.cart))
//                .setInactiveIconResource(R.drawable.tab_ic_shop_default)
//                .setActiveColorResource(R.color.mainColor)
//                .setInActiveColorResource(R.color.black)
//
//        mTextBadge = TextBadgeItem()
//        shopItem.setBadgeItem(mTextBadge)
//
//        val standingItem =
//            BottomNavigationItem(R.drawable.tab_ic_list_selected, resources.getString(R.string.my_business))
//                .setInactiveIconResource(R.drawable.tab_ic_list_default)
//                .setActiveColorResource(R.color.mainColor)
//                .setInActiveColorResource(R.color.black)
//
//        mShapeBadge = ShapeBadgeItem()
//        mShapeBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)
//        standingItem.setBadgeItem(mShapeBadge)
//
//        //设置底部导航模式及样式
//        setMode(BottomNavigationBar.MODE_FIXED)
//        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
//        setBarBackgroundColor(R.color.white)
//        //添加Tab
//        addItem(homeItem)
//            .addItem(foundItem)
//            .addItem(mainItem)
//            .addItem(shopItem)
//            .addItem(standingItem)
//            .setFirstSelectedPosition(0)
//            .initialise()
//    }
//
//    /*
//        检查购物车Tab是否显示标签
//     */
//    fun checkTextBadge(count: Int) {
//        if (count == 0) {
//            mTextBadge.hide()
//        } else {
//            mTextBadge.show()
//            mTextBadge.setText("$count")
//        }
//    }
//
//    /*
//        检查消息Tab是否显示标签
//     */
//    fun checkShapeBadge(isVisiable: Boolean) {
//        if (isVisiable) {
//            mShapeBadge.show()
//        } else {
//            mShapeBadge.hide()
//        }
//    }

}