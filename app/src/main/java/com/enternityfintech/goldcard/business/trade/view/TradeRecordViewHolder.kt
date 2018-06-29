package com.enternityfintech.goldcard.business.trade.view

import android.view.LayoutInflater
import android.view.View
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.business.trade.mode.TradeRecordModel
import com.enternityfintech.goldcard.widget.recyclerview.DataViewHolder
import com.enternityfintech.goldcard.widget.recyclerview.LayoutId
import kotlinx.android.synthetic.main.holder_trade_record.view.*
import kotlinx.android.synthetic.main.holder_trade_record_item.view.*

@LayoutId(R.layout.holder_trade_record)
class TradeRecordViewHolder(itemView: View) : DataViewHolder<TradeRecordModel>(itemView) {

    override fun updateViewByData(data: TradeRecordModel) {
        super.updateViewByData(data)
        val inflater = LayoutInflater.from(itemView.context)
        itemView.tv_month.text = data.month
        itemView.ll_container.removeAllViews()
        for (item in data.items!!) {
            val view = inflater.inflate(R.layout.holder_trade_record_item, itemView.ll_container, false)
            view.iv_icon.setImageResource(item.icon)
            view.tv_title.text = item.title
            view.tv_time.text = item.date
            view.tv_weight.text = item.weight
            view.tv_weight.setTextColor(item.color)
            itemView.ll_container.addView(view)
        }
    }
}