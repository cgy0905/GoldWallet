package com.enternityfintech.goldcard.business.trade.view

import android.content.Intent
import android.os.Bundle
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.business.trade.mode.TradeRecordModel
import com.enternityfintech.goldcard.business.trade.presenter.TradePresenter
import com.enternityfintech.goldcard.business.trade.view.iv.ITradeRecordView
import com.enternityfintech.goldcard.ui.base.BaseStatusBarActivity
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerAdapter
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerHelper
import kotlinx.android.synthetic.main.activity_trade_record.*

class TradeRecordActivity : BaseStatusBarActivity(), ITradeRecordView {

    private val presenter = TradePresenter(this)
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_record)
        adapter = RecyclerHelper.configRecyclerAdapter(recyclerView)
        adapter.registerViewHolder(TradeRecordModel::class.java, TradeRecordViewHolder::class.java)
        adapter.registerClickListener { _, _ ->
            startActivity(Intent(this, TradeDetailActivity::class.java))
        }
        presenter.onCreate()
    }

    override fun showRecord(data: List<TradeRecordModel>) {
        adapter.updateData(data)
    }
}
