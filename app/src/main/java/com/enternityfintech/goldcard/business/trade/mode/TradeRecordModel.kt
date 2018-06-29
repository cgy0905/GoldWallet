package com.enternityfintech.goldcard.business.trade.mode

import com.enternityfintech.goldcard.app.base.mvp.IModel

/**
 *Created by cool on 2018/6/29
 */
class TradeRecordModel : IModel {

    var month: String? = null
    var items: List<Record>? = null

    class Record : IModel {
        var icon: Int = 0
        var title: String? = null
        var date: String? = null
        var weight: String? = null
        var color: Int = 0
    }
}