package com.enternityfintech.goldcard.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.TextView
import com.enternityfintech.goldcard.R
import kotlinx.android.synthetic.main.layout_gold_card_picker.view.*

/**
 *Created by cool on 2018/6/27
 */
class GoldCardPicker @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), CompoundButton.OnCheckedChangeListener {

    private val gram100Tv: TextView
    private val gram200Tv: TextView
    private val gram500Tv: TextView

    private val gram100Av: AmountView
    private val gram200Av: AmountView
    private val gram500Av: AmountView

    init {
        orientation = VERTICAL
        setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        val view = LayoutInflater.from(context).inflate(R.layout.layout_gold_card_picker, this, true)

        view.cb_gram_100.setOnCheckedChangeListener(this)
        view.cb_gram_200.setOnCheckedChangeListener(this)
        view.cb_gram_500.setOnCheckedChangeListener(this)

        gram100Tv = view.tv_gram_100
        gram200Tv = view.tv_gram_200
        gram500Tv = view.tv_gram_500

        gram100Av = view.add_sub_gram_100
        gram200Av = view.add_sub_gram_200
        gram500Av = view.add_sub_gram_500

    }

    fun setGram100Count(count: Int) {
        gram100Tv.text = resources.getString(R.string.piece_count, count)
        gram100Av.setMaxAmount(count)
    }

    fun setGram200Count(count: Int) {
        gram200Tv.text = resources.getString(R.string.piece_count, count)
        gram200Av.setMaxAmount(count)
    }

    fun setGram500Count(count: Int) {
        gram500Tv.text = resources.getString(R.string.piece_count, count)
        gram500Av.setMaxAmount(count)
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.cb_gram_100 -> gram100Av.isEnabled = isChecked
            R.id.cb_gram_200 -> gram200Av.isEnabled = isChecked
            R.id.cb_gram_500 -> gram500Av.isEnabled = isChecked
        }
    }
}