package com.enternityfintech.goldcard.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.enternityfintech.goldcard.R
import kotlinx.android.synthetic.main.view_text.view.*

/**
 *Created by cool on 2018/5/18
 */
class ViewText @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
        val view = LayoutInflater.from(context).inflate(R.layout.view_text, this, true)
        val typedArray = resources.obtainAttributes(attrs, R.styleable.ViewText)
        val icon = typedArray.getResourceId(R.styleable.ViewText_icon, 0)
        if (icon != 0) {
            view.iv_icon.setImageResource(icon)
        }
        val text = typedArray.getString(R.styleable.ViewText_text)
        if (!text.isNullOrEmpty()) {
            view.tv_text.text = text
        }
        typedArray.recycle()
    }
}