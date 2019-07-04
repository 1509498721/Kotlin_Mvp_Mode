package com.whz.main.presenter.view

import com.whz.base.presenter.view.BaseView
import com.whz.main.data.bean.HomeMessage

/**
 *Created by whz  on 2019-06-26
 */
interface HomeFragmentView : BaseView {
    fun getHomeMessage(homeMessage: List<HomeMessage>)
}