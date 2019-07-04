package com.whz.found.presenter

import com.whz.base.ext.execute
import com.whz.base.presenter.BasePresenter
import com.whz.base.rx.BaseObserver
import com.whz.found.data.bean.Exploration
import com.whz.found.presenter.view.FoundFragmentView
import com.whz.found.service.FoundService
import javax.inject.Inject

/**
 *Created by whz  on 2019-06-26
 */
class FoundFragmentPresenter @Inject constructor() : BasePresenter<FoundFragmentView>() {

    @Inject
    lateinit var foundService: FoundService


    fun getCommentList(pageNum: Int, pageSize: Int) {
        if (!isNetWork()) {
            return
        }
        foundService.commentListService(pageNum,pageSize)
            .execute(object : BaseObserver<List<Exploration>>(mView) {
                override fun onNext(t: List<Exploration>) {
                    mView.getCommentList(t)
                }
            }, lifecycleProvider)
    }
}