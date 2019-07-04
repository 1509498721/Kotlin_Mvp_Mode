package com.whz.usercenter.ui.activity

import android.os.Bundle
import android.text.TextUtils
import com.orhanobut.hawk.Hawk
import com.whz.base.common.BaseConstant
import com.whz.base.ext.OnClick
import com.whz.base.ui.activity.BaseMvpActivity
import com.whz.usercenter.R
import com.whz.usercenter.data.bean.LoginBean
import com.whz.usercenter.injection.component.DaggerUserComponent
import com.whz.usercenter.injection.module.UserModule
import com.whz.usercenter.presenter.LoginPresenter
import com.whz.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun initView() {

    }

    override fun initData() {
        btnLogin.OnClick {
            if (TextUtils.isEmpty(edPhone.text.toString().trim())) {
                topShow(getString(R.string.please_input_phone))
                return@OnClick
            }
            if (TextUtils.isEmpty(edPassword.text.toString().trim())) {
                topShow(getString(R.string.please_input_pwd))
                return@OnClick
            }
            mPresenter.login(edPhone.text.toString(), edPassword.text.toString())
        }
    }


    override fun onLoginResult(data: LoginBean) {
        saveUserMessage(data)
    }

    private fun saveUserMessage(data: LoginBean) {
        Hawk.put(BaseConstant.SESSION_ID, data.sessionId)
    }
}
