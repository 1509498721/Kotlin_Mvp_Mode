package com.whz.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.whz.base.R
import org.jetbrains.anko.find

/**
 *Created by whz  on 2019-06-27
 */
class ProgressLoading private constructor(context: Context, theme: Int) : Dialog(context, theme) {
    companion object {
        private lateinit var mDialog: ProgressLoading
        private var animDrawable: AnimationDrawable? = null
        fun create(context: Context): ProgressLoading {
            /* mDialog = ProgressLoading(context, R.style.dialog)
             mDialog.setContentView(R.layout.foot_view)
             mDialog.setCancelable(true)
             mDialog.setCanceledOnTouchOutside(false)
             mDialog.window.attributes.gravity = Gravity.CENTER
             val lp = mDialog.window.attributes
             lp.dimAmount = 0.2f
             mDialog.window.attributes = lp
             return mDialog*/
            //图片为动画时候
            //样式引入
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            //设置布局
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER
            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f
            //设置属性
            mDialog.window.attributes = lp

            //获取动画视图
            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
            animDrawable = loadingView.background as AnimationDrawable

            return mDialog
        }
    }

    fun showLoad() {
        super.show()
        animDrawable?.start()
    }

    fun hideLoad() {
        super.dismiss()
        animDrawable?.stop()
    }
}