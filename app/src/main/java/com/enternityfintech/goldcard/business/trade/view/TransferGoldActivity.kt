package com.enternityfintech.goldcard.business.trade.view

import android.content.Intent
import android.os.Bundle
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.base.BaseStatusBarActivity


import com.enternityfintech.goldcard.business.capture.view.CaptureActivity
import kotlinx.android.synthetic.main.activity_transfer_gold.*

class TransferGoldActivity : BaseStatusBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_gold)

        cardPicker.setGram100Count(10)
        cardPicker.setGram200Count(5)
        cardPicker.setGram500Count(2)

        ib_scan.setOnClickListener {
            startActivity(Intent(this,CaptureActivity::class.java))
        }
    }
}
