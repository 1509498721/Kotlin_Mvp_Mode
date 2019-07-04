package com.whz.shopping.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whz.base.ui.fragment.BaseFragment
import com.whz.base.ui.fragment.BaseMvpFragment
import com.whz.shopping.R
import com.whz.shopping.data.bean.CartList
import com.whz.shopping.injection.component.DaggerShoppingComponent
import com.whz.shopping.injection.module.ShoppingModule
import com.whz.shopping.presenter.ShoppingFragmentPresenter
import com.whz.shopping.presenter.view.ShoppingFragmentView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ShoppingCartFragment : BaseMvpFragment<ShoppingFragmentPresenter>(), ShoppingFragmentView {

    override fun injectComponent() {

        DaggerShoppingComponent.builder().activityComponent(mActivityComponent).shoppingModule(ShoppingModule()).build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun getshoppingCarList(shoppingCarList: CartList) {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getshoppingCarList()
    }
}
