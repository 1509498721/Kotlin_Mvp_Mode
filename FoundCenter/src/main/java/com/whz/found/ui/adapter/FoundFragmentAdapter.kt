package com.whz.found.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.whz.base.ext.loadUrl
import com.whz.base.ui.adapter.BaseRecyclerViewAdapter
import com.whz.found.R
import com.whz.found.data.bean.Exploration
import kotlinx.android.synthetic.main.item_exploration.view.*

/**
 *Created by whz  on 2019-06-28
 */
class FoundFragmentAdapter(context: Context) :
    BaseRecyclerViewAdapter<Exploration, FoundFragmentAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoundFragmentAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.item_exploration, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val mode=dataList[position]
        holder.itemView.ivExpImg.loadUrl(mode.goodsDefaultImage!!)
        holder.itemView.tvExpTitle.text=mode.goodsName
        holder.itemView.tvLike.text=mode.goodsPraiseNum.toString()
        holder.itemView.tvComment.text=mode.shopCommentNum.toString()
        if (mode.hasPraise){
            holder.itemView.ivLike.setImageResource(R.mipmap.ic_like_selected)
        }else{
            holder.itemView.ivLike.setImageResource(R.mipmap.ic_like_default)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}