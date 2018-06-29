package com.enternityfintech.goldcard.widget.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created by cool on 18/4/18.
 * Child class must public static inner class or public outer class.
 */
abstract class DataViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var clickObserver: ((viewId: Int, data: Any?) -> Unit)? = null

    protected var data: D? = null
        private set

    open fun updateViewByData(data: D) {
        this.data = data
    }

    /**
     * bind with item data
     */
    fun registerClickEvent(view: View, clickListener: View.OnClickListener? = null) {
        view.setOnClickListener {
            clickListener?.onClick(view)
            clickObserver?.invoke(view.id, data)
        }
    }

    /**
     * bind with special data
     */
    fun registerClickEvent(view: View, data: Any, clickListener: View.OnClickListener? = null) {
        view.setOnClickListener {
            clickListener?.onClick(view)
            clickObserver?.invoke(view.id, data)
        }
    }
}
