package com.enternityfintech.goldcard.utils.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.enternityfintech.goldcard.R
import kotlinx.android.synthetic.main.dialog_month_picker.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *Created by cool on 2018/6/20
 */
class DatePickerDialog(context: Context) : Dialog(context, R.style.CustomDialog), View.OnClickListener {

    var onDateSelectedListener: ((month: String) -> Unit)? = null

    var selectedMonth: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.dialog_month_picker)
        btn_ok.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        val window = window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.AnimBottom)
        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = lp
        val items: ArrayList<String> = ArrayList()
        items.add(context.getString(R.string.all_month))
        val calendar = Calendar.getInstance()
        for (i in 0..36) {
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.MONTH, -i)
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            items.add(year.toString() + " - " + if (month < 10) "0$month" else month)
        }
        wheelView.setData(items)
        var selection: Int
        if (selectedMonth.isNullOrEmpty()) {
            selection = 0
        } else {
            selection = items.indexOf(selectedMonth)
            selection = if (selection >= 0) selection else 0
        }
        wheelView.setDefault(selection)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_ok -> {
                onDateSelectedListener?.invoke(wheelView.selectedText)
            }
        }
        dismiss()
    }
}