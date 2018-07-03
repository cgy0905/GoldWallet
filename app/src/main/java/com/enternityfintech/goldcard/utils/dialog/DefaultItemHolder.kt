package com.enternityfintech.goldcard.utils.dialog

import android.view.View
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.widget.recyclerview.DataViewHolder
import com.enternityfintech.goldcard.widget.recyclerview.LayoutId
import kotlinx.android.synthetic.main.popup_item.view.*

@LayoutId(R.layout.popup_item)
class DefaultItemHolder(view: View) : DataViewHolder<String>(view) {
    override fun updateViewByData(data: String) {
        super.updateViewByData(data)
        itemView.tv_item.text = data
        registerClickEvent(itemView.tv_item)
    }
}