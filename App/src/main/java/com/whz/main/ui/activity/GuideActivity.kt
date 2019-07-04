package com.whz.main.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.orhanobut.hawk.Hawk
import com.whz.base.ext.OnClick
import com.whz.base.ui.activity.BaseActivity
import com.whz.main.R
import com.whz.provider.common.HawkConstants
import kotlinx.android.synthetic.main.activity_guide.*
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class GuideActivity : BaseActivity() {

    /**
     * 引导页图片
     */
    private val pages = arrayOf(R.mipmap.home_guide2, R.mipmap.home_guide2)
    private var viewList: ArrayList<View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        viewList = ArrayList()
        val adapter = GuidePageAdapter()
        mViewPager.adapter = adapter
        for (page in pages) {
            val iv = ImageView(this)
            iv.setImageResource(page)
            val params =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            iv.layoutParams = params
            iv.scaleType = ImageView.ScaleType.CENTER_CROP//图片按等比缩放
            viewList!!.add(iv)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.transparent)
        }
        btnConfirm.OnClick {
            Hawk.put(HawkConstants.FIRSTIN, false)
            startActivity<MainActivity>()
            finish()
        }
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    view1.setBackgroundResource(R.drawable.draw_cirle_white_3)
                    view2.setBackgroundResource(R.drawable.draw_cirle_white_2)
                } else if (position == 1) {
                    view1.setBackgroundResource(R.drawable.draw_cirle_white_2)
                    view2.setBackgroundResource(R.drawable.draw_cirle_white_3)
                }
                if (position == pages.size - 1) {//最后一页
                    btnConfirm.visibility = View.VISIBLE
                    llCircle.visibility = View.GONE
                } else {
                    btnConfirm.visibility = View.GONE
                    llCircle.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private inner class GuidePageAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return pages.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(viewList!![position])//删除页卡
        }

        override fun getItemPosition(`object`: Any): Int {
            return super.getItemPosition(`object`)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(viewList!![position])
            return viewList!![position]
        }
    }
}
