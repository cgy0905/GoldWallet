package com.enternityfintech.goldcard.widget.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.enternityfintech.goldcard.R
import com.enternityfintech.goldcard.widget.recyclerview.loadmore.LoadMore
import com.enternityfintech.goldcard.widget.recyclerview.loadmore.LoadMoreAdapter
import com.enternityfintech.goldcard.widget.recyclerview.loadmore.LoadMoreViewHolder

/**
 *Created by cool on 2018/6/29
 */
object RecyclerHelper {

    @JvmStatic
    fun configRecyclerAdapter(recyclerView: RecyclerView): RecyclerAdapter {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        return adapter
    }

    @JvmStatic
    fun configLoadmoreAdapter(recyclerView: RecyclerView): LoadMoreAdapter {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val adapter = LoadMoreAdapter()
        adapter.registerViewHolder(Empty::class.java, EmptyViewHolder::class.java)
        adapter.registerViewHolder(LoadMore::class.java, LoadMoreViewHolder::class.java)
        adapter.setLoadMore(LoadMore(recyclerView.context.getString(R.string.no_more_data)))
        adapter.empowerLoadMoreAbility(recyclerView)
        return adapter
    }
}