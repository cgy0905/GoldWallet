package com.enternityfintech.goldcard.utils.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.widget.recyclerview.DataViewHolder
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerAdapter
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerHelper
import kotlinx.android.synthetic.main.popup.view.*

/**
 * Created by cool on 2018/6/20
 */
class DropDownPopup(context: Context) : PopupWindow(context) {

    private val adapter: RecyclerAdapter
    private val bgView: View

    init {
        contentView = LayoutInflater.from(context).inflate(R.layout.popup, null, false)
        bgView = contentView.bg_view
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        setBackgroundDrawable(ColorDrawable(Color.WHITE))

        isFocusable = true
        isOutsideTouchable = true

        adapter = RecyclerHelper.configRecyclerAdapter(contentView.recyclerView)
        adapter.registerViewHolder(String::class.java, DefaultItemHolder::class.java)

        bgView.setOnClickListener { dismiss() }
    }

    fun registerItemClickListener(observer: (viewId: Int, data: Any?) -> Unit) {
        adapter.registerClickListener({ viewId, data ->
            observer.invoke(viewId, data)
            dismiss()
        })
    }

    fun registerItemClickListener(observer: (data: String) -> Unit) {
        adapter.registerClickListener({ _, data ->
            observer.invoke(data.toString())
            dismiss()
        })
    }

    fun registerHolder(dataClazz: Class<*>, clazz: Class<out DataViewHolder<*>>) {
        adapter.registerViewHolder(dataClazz, clazz)
    }

    /**
     * It's not need to register viewHolder when data is List<String>, others else
     */
    fun setData(data: List<Any>) {
        adapter.updateData(data)
    }

    override fun showAsDropDown(anchor: View?) {
        super.showAsDropDown(anchor)
        setBackgroundAlpha(true)
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int) {
        super.showAsDropDown(anchor, xoff, yoff)
        setBackgroundAlpha(true)
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
        setBackgroundAlpha(true)
    }

    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
        super.showAtLocation(parent, gravity, x, y)
        setBackgroundAlpha(true)
    }

    private fun setBackgroundAlpha(visible: Boolean) {
        //FIXME add anim here
        bgView.postDelayed({
            bgView.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        }, ANIM_TIME)
    }

    companion object {
        const val ANIM_TIME = 300L
    }
}