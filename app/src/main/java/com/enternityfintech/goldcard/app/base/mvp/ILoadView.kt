package com.enternityfintech.goldcard.app.base.mvp

/**
 * Created by cool on 2018/3/8.
 */
interface ILoadView : IView {

    fun showLoading() {}

    fun hideLoading() {}

    fun showToast(msg: String) {}

    fun showError(throwable: Throwable) {}

}