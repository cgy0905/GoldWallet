package com.enternityfintech.goldcard.business.trade.view

import android.os.Bundle
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.business.trade.mode.TradeDetailModel
import com.enternityfintech.goldcard.business.trade.presenter.TradeDetailPresenter
import com.enternityfintech.goldcard.business.trade.view.iv.ITradeDetailView
import com.enternityfintech.goldcard.base.BaseStatusBarActivity
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerAdapter
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerHelper
import kotlinx.android.synthetic.main.activity_trade_detail.*

class TradeDetailActivity : BaseStatusBarActivity(), ITradeDetailView {

    private val presenter = TradeDetailPresenter(this)
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_detail)
        adapter = RecyclerHelper.configRecyclerAdapter(recyclerView)
        adapter.registerViewHolder(TradeDetailModel::class.java, TradeDetailViewHolder::class.java)
        presenter.onCreate()
    }

}
