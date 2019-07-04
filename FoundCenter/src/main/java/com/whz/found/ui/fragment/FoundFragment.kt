package com.whz.found.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whz.base.ui.fragment.BaseFragment
import com.whz.base.ui.fragment.BaseMvpFragment
import com.whz.base.widgets.GridSpaceItemDecoration

import com.whz.found.R
import com.whz.found.data.bean.Exploration
import com.whz.found.injection.component.DaggerFoundComponent
import com.whz.found.injection.module.FoundModule
import com.whz.found.presenter.FoundFragmentPresenter
import com.whz.found.presenter.view.FoundFragmentView
import com.whz.found.ui.adapter.FoundFragmentAdapter
import dagger.internal.DaggerCollections
import kotlinx.android.synthetic.main.fragment_found.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FoundFragment : BaseMvpFragment<FoundFragmentPresenter>(), FoundFragmentView {

    private val pageSize = 10
    private var pageNum = 1
    private lateinit var mAdapter: FoundFragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_found, container, false)
    }


    private fun initView() {
        mPresenter.getCommentList(pageNum, pageSize)
    }

    override fun getCommentList(foundList: List<Exploration>) {

        foundRecycler.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        foundRecycler.addItemDecoration(GridSpaceItemDecoration(20, 20))
        mAdapter = FoundFragmentAdapter(context!!)
        mAdapter.setData(foundList as MutableList<Exploration>)

        foundRecycler.adapter = mAdapter

    }

    override fun onResume() {
        super.onResume()
        pageNum = 1
        initView()
    }


    override fun injectComponent() {
        DaggerFoundComponent.builder().activityComponent(mActivityComponent).foundModule(FoundModule()).build()
            .inject(this)
        mPresenter.mView = this
    }


}
