package com.enternityfintech.goldcard.widget.recyclerview.loadmore

/**
 *Created by cool on 2018/6/4
 */
interface ILoadMore {

    fun onLoadMore() {}

    fun setDefaultCount(defaultCount: Int) {}

    fun enableLoadMore(enable: Boolean) {}
}