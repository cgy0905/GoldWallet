package com.enternityfintech.goldcard.business.trade.view

import com.enternityfintech.goldcard.app.base.mvp.ILoadView
import com.enternityfintech.goldcard.business.trade.mode.TradeRecordModel

/**
 *Created by cool on 2018/6/29
 */
interface ITradeRecordView : ILoadView {
    fun showRecord(data: List<TradeRecordModel>)
}