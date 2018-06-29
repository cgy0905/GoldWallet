package com.enternityfintech.goldcard.widget.recyclerview.loadmore

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.enternityfintech.goldcard.widget.recyclerview.RecyclerAdapter

/**
 * Created by cool on 18/4/18.
 */
class LoadMoreAdapter : RecyclerAdapter() {

    private var loadMoreListener: (() -> Unit)? = null
    private var loadData: Any? = null
    private var defaultCount = 15
    private var loadMoreAble = false


    override fun doNotifyDataSetChanged() {
        if (loadData != null) {
            data.remove(loadData!!)
            if (data.size > defaultCount && !loadMoreAble) {
                data.add(loadData!!)
            }
        }
        notifyDataSetChanged()
    }

    /**
     * Use for load more
     */
    fun setLoadMore(data: Any?) {
        this.loadData = data
    }

    /**
     * Use for load more
     * Maximum data per request
     */
    fun setDefaultCount(defaultCount: Int) {
        this.defaultCount = defaultCount
    }

    /**
     * Use for load more
     */
    fun enableLoadMore(loadMoreAble: Boolean) {
        this.loadMoreAble = loadMoreAble
        doNotifyDataSetChanged()
    }

    /**
     * Use for load more
     * @callback
     */
    fun setLoadMoreListener(listener: (() -> Unit)?) {
        this.loadMoreListener = listener
    }

    /**
     * Use for load more
     */
    fun empowerLoadMoreAbility(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!loadMoreAble) return
                if (itemCount < defaultCount) return
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager) {
                    val lastItemPosition = layoutManager.findLastVisibleItemPosition()
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition + 1 == itemCount) {
                        loadMoreListener?.invoke()
                    }
                }
            }
        })
    }

    val lastData: Any?
        get() = if (data.isEmpty()) {
            null
        } else data[data.size - 1]
}
