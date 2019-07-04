package com.whz.main.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import com.whz.base.common.AppManager
import com.whz.base.ui.activity.BaseActivity
import com.whz.found.ui.fragment.FoundFragment
import com.whz.main.R
import com.whz.main.ui.fragment.HomeFragment
import com.whz.order.ui.fragment.OrderFragment
import com.whz.shopping.ui.fragment.ShoppingCartFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {
    private var mExitTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HomeFragment::class.java.name)
        initView()
        changeStatusBarTextColor(true)
    }

    private fun initView() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbHome -> {
                    replaceFragment(HomeFragment::class.java.name)
                }
                R.id.rbExploration -> {
                    replaceFragment(FoundFragment::class.java.name)
                }
                R.id.rbPurchasing -> {
                    radioGroup.check(R.id.rbHome)
                }
                R.id.rbCart -> {
                    replaceFragment(ShoppingCartFragment::class.java.name)
                }
                R.id.rbStandingList -> {
                    replaceFragment(OrderFragment::class.java.name)
                }
            }
        }
    }


    private fun replaceFragment(tag: String) {
        var tempFragment = supportFragmentManager.findFragmentByTag(tag)
        val transaction = supportFragmentManager.beginTransaction()
        if (tempFragment == null) {
            try {
                tempFragment = Class.forName(tag).newInstance() as Fragment
                transaction.add(R.id.fragment, tempFragment, tag)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (i in fragments.indices) {
                val fragment = fragments[i]
                if (fragment.tag == tag) {
                    transaction.show(fragment)
                } else {
                    transaction.hide(fragment)
                }
            }
        }
        transaction.commitAllowingStateLoss()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast(getString(R.string.backpress_to_exit))
                mExitTime = System.currentTimeMillis()
            } else {
                AppManager.instance.finishAllActivity()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
