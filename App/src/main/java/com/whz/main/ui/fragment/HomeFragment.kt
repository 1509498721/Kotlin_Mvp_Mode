package com.whz.main.ui.fragment


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.orhanobut.hawk.Hawk
import com.whz.base.ext.OnClick
import com.whz.base.ui.fragment.BaseMvpFragment
import com.whz.base.utils.glide.GlideApp
import com.whz.main.R
import com.whz.main.data.bean.HomeMessage
import com.whz.main.injection.component.DaggerMainComponent
import com.whz.main.injection.module.MainModule
import com.whz.main.presenter.HomeFragmentPresenter
import com.whz.main.presenter.view.HomeFragmentView
import com.whz.provider.common.HawkConstants
import com.whz.usercenter.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_title.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import java.util.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseMvpFragment<HomeFragmentPresenter>(), HomeFragmentView {
    private val homeMessageList = ArrayList<HomeMessage>()
    private val mData = ArrayList<String>()
    private val languages = arrayOf("简体中文", "繁体中文", "English")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().activityComponent(mActivityComponent).mainModule(MainModule()).build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onResume() {
        super.onResume()
        initData()
        initView()
    }

    private fun initData() {
        btnLogin.OnClick {startActivity<LoginActivity>()}
    }

    private fun initView() {
        val path = Hawk.get<String>(HawkConstants.HOMEBACKGROUND)
        mPresenter.getHomeMssage(1)
        GlideApp.with(this).applyDefaultRequestOptions(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
            .load(path).into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    imgBack.background = resource
                }
            })
        val language = Hawk.get(HawkConstants.LANGUAGE, "zh_CN")
        when (language) {
            "zh_CN" -> tvLanguage.text = languages[0]
            "zh_TW" -> tvLanguage.text = languages[1]
            "en" -> tvLanguage.text = languages[2]
        }
    }

    override fun getHomeMessage(homeMessage: List<HomeMessage>) {
        homeMessageList.addAll(homeMessage)
        for (data in homeMessage) {
            mData.add(data.messageName!!)
        }
        tvBanner.setData(mData)
    }
}
