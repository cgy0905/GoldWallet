package com.enternityfintech.goldcard.business.trade.view

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.base.BaseStatusBarActivity
import com.enternityfintech.goldcard.business.trade.mode.TradeRecordModel
import com.enternityfintech.goldcard.business.trade.presenter.TradePresenter
import com.enternityfintech.goldcard.business.trade.view.iv.ITradeRecordView
import com.enternityfintech.goldcard.ui.dialog.ToastHelper
import com.enternityfintech.goldcard.utils.dialog.DatePickerDialog
import com.enternityfintech.goldcard.utils.dialog.DropDownPopup
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerAdapter
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerHelper
import kotlinx.android.synthetic.main.activity_trade_record.*

class TradeRecordActivity : BaseStatusBarActivity(), ITradeRecordView, CompoundButton.OnCheckedChangeListener {

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

        fl_type1.setOnClickListener {
            rb1.isChecked = true
            rb2.isChecked = false
            rb3.isChecked = false
        }
        fl_type2.setOnClickListener {
            rb1.isChecked = false
            rb2.isChecked = true
            rb3.isChecked = false
        }
        fl_type3.setOnClickListener {
            rb1.isChecked = false
            rb2.isChecked = false
            rb3.isChecked = true
        }
        rb1.setOnCheckedChangeListener(this)
        rb2.setOnCheckedChangeListener(this)
        rb3.setOnCheckedChangeListener(this)
    }

    override fun showRecord(data: List<TradeRecordModel>) {
        adapter.updateData(data)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            when (buttonView) {
                rb1 -> {
                    val popup = DropDownPopup(this)
                    popup.setData(presenter.mockPicker())
                    popup.showAsDropDown(rb1)
                    popup.registerItemClickListener { data: String ->
                        ToastHelper.showToast(this, data)
                    }
                    popup.setOnDismissListener {
                        rb1.isChecked = false
                    }
                }
                rb2 -> {
                    val popup = DropDownPopup(this)
                    popup.setData(presenter.mockPicker())
                    popup.showAsDropDown(rb2)
                    popup.registerItemClickListener { data: String ->
                        ToastHelper.showToast(this, data)
                    }
                    popup.setOnDismissListener {
                        rb2.isChecked = false
                    }
                }
                rb3 -> {
                    val dialog = DatePickerDialog(this)
                    dialog.setOnDismissListener {
                        rb3.isChecked = false
                    }
                    dialog.onDateSelectedListener = {
                        ToastHelper.showToast(this, it)
                    }
                    dialog.show()
                }
            }
        }
    }
}
