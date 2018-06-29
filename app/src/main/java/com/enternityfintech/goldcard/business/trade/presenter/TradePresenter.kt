package com.enternityfintech.goldcard.business.trade.presenter

import android.support.v4.content.ContextCompat
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.app.base.BaseApp
import com.enternityfintech.goldcard.app.base.mvp.Presenter
import com.enternityfintech.goldcard.business.trade.mode.TradeRecordModel
import com.enternityfintech.goldcard.business.trade.view.iv.ITradeRecordView
import com.enternityfintech.goldcard.utils.DateUtil
import java.util.*

/**
 *Created by cool on 2018/6/29
 */
class TradePresenter(view: ITradeRecordView) : Presenter<ITradeRecordView>(view) {

    override fun onCreate() {
        super.onCreate()
        val data = mockData()
        view.showRecord(data)
    }

    private fun mockData(): List<TradeRecordModel> {
        val mock = ArrayList<TradeRecordModel>()
        val random = Random()
        val size = random.nextInt(5) + 3
        val range = random.nextInt(3) + 3
        val icons = intArrayOf(R.drawable.ic_maijin, R.drawable.ic_sell, R.drawable.ic_shoujin, R.drawable.ic_zhuanjin)
        for (i in 0 until size) {
            val record = TradeRecordModel()
            record.month = DateUtil.formatYMDHm(System.currentTimeMillis() - random.nextInt(99999))
            val items = ArrayList<TradeRecordModel.Record>()
            for (j in 0 until range) {
                val item = TradeRecordModel.Record()
                item.date = DateUtil.formatYMDHm(System.currentTimeMillis() - random.nextInt(99999))
                item.icon = icons[random.nextInt(icons.size)]
                item.title = "类型"
                item.weight = "+" + random.nextInt(1000) + "克"
                if (random.nextBoolean()) {
                    item.color = ContextCompat.getColor(BaseApp.getContext(), R.color.main_color)
                } else {
                    item.color = ContextCompat.getColor(BaseApp.getContext(), R.color.title)
                }

                items.add(item)
            }
            record.items = items
            mock.add(record)
        }
        return mock
    }
}