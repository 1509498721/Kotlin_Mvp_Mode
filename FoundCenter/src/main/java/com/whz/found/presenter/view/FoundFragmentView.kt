package com.whz.found.presenter.view

import com.whz.base.presenter.view.BaseView
import com.whz.found.data.bean.Exploration

/**
 *Created by whz  on 2019-06-26
 */
interface FoundFragmentView : BaseView {
    fun getCommentList(foundList: List<Exploration>)
}