package com.enternityfintech.goldcard.business.trade.view

import android.view.View
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.business.trade.mode.TradeDetailModel
import com.enternityfintech.goldcard.widget.recyclerview.DataViewHolder
import com.enternityfintech.goldcard.widget.recyclerview.LayoutId

/**
 *Created by cool on 2018/6/29
 */
@LayoutId(R.layout.holder_trade_detail)
class TradeDetailViewHolder(view: View) : DataViewHolder<TradeDetailModel>(view) {
    override fun updateViewByData(data: TradeDetailModel) {
        super.updateViewByData(data)
    }
}