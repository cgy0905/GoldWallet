package com.enternityfintech.goldcard.widget

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.utils.ScreenUtils
import kotlinx.android.synthetic.main.common_title.view.*

/**
 *Created by cool on 2018/5/16
 */
class TitleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private val titleTv: TextView
    private val sureBtn: Button
    private val backIv: ImageView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.common_title, this, false)
        addView(view)
        titleTv = view.tv_title
        sureBtn = view.btn_sure
        backIv = view.iv_back
        val typedArray = resources.obtainAttributes(attrs, R.styleable.TitleView)

        val submitRes = typedArray.getResourceId(R.styleable.TitleView_submitRes, 0)
        if (submitRes > 0) {
            sureBtn.visibility = View.VISIBLE
            sureBtn.background = null
            sureBtn.text = null
            sureBtn.setBackgroundResource(submitRes)
        }

        val isImmersion = typedArray.getBoolean(R.styleable.TitleView_isImmersion, true)
        if (isImmersion) {
            val statusBarHeight = ScreenUtils.getStatusBarHeight(context)
            setPadding(0, statusBarHeight, 0, 0)
        }

        val backResId = typedArray.getResourceId(R.styleable.TitleView_backDrawable, 0)
        if (backResId != 0) {
            backIv.setImageResource(backResId)
        }
        val title = typedArray.getString(R.styleable.TitleView_title)
        if (!title.isNullOrEmpty()) {
            titleTv.text = title
        } else {
            if (context is Activity) {
                titleTv.text = context.title
            }
        }
        val titleColor = typedArray.getColor(R.styleable.TitleView_titleColor, resources.getColor(R.color.black))
        titleTv.setTextColor(titleColor)

        val submitColor = typedArray.getColor(R.styleable.TitleView_submitColor, resources.getColor(R.color.black))
        sureBtn.setTextColor(submitColor)
        val submit = typedArray.getString(R.styleable.TitleView_submit)
        if (!submit.isNullOrEmpty()) {
            sureBtn.visibility = View.VISIBLE
            sureBtn.text = submit
        }

        val bg = typedArray.getColor(R.styleable.TitleView_bg, resources.getColor(R.color.white))
        view.setBackgroundColor(bg)
        setBackgroundColor(bg)
        typedArray.recycle()

        backIv.setOnClickListener {
            if (context is Activity) {
                context.finish()
            }
        }
    }

    fun setBackResouce(resId: Int): TitleView {
        backIv.setImageResource(resId)
        return this
    }

    fun setSubmitText(resId: Int): TitleView {
        sureBtn.setText(resId)
        return this
    }

    fun setTitle(resId: Int): TitleView {
        titleTv.setText(resId)
        return this
    }

    fun setTitle(title: String): TitleView {
        titleTv.text = title
        return this
    }

    fun setTitleColor(color: Int): TitleView {
        titleTv.setTextColor(color)
        return this
    }

    fun setTitleColorRes(resId: Int): TitleView {
        titleTv.setTextColor(ContextCompat.getColor(context, resId))
        return this
    }

    fun setBackListener(listener: OnClickListener): TitleView {
        backIv.setOnClickListener(listener)
        return this
    }

    fun setSubmitListener(listener: OnClickListener): TitleView {
        sureBtn.visibility = View.VISIBLE
        sureBtn.setOnClickListener(listener)
        return this
    }

    fun setBackBtnVisible(visible: Int) {
        backIv.visibility = visible
    }
}