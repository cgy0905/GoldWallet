package com.enternityfintech.goldcard.widget.recyclerview.loadmore

import android.view.View
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.widget.recyclerview.DataViewHolder
import com.enternityfintech.goldcard.widget.recyclerview.LayoutId
import kotlinx.android.synthetic.main.loadmore.view.*

@LayoutId(R.layout.loadmore)
class LoadMoreViewHolder(view: View) : DataViewHolder<LoadMore>(view) {

    override fun updateViewByData(data: LoadMore) {
        super.updateViewByData(data)
        itemView.tv_load.text = data.data
    }
}