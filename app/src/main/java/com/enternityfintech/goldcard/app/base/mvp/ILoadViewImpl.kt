package com.enternityfintech.goldcard.app.base.mvp

import android.content.Context
import com.enternityfintech.goldcard.ui.dialog.ToastHelper

/**
 * Created by cool on 2018/3/8.
 */
interface ILoadViewImpl : ILoadView {

    /**
     * override this if you want unify handling dialog and toast
     * if show dialog , do not return application's context
     */
    fun withContext(): Context

    override fun showLoading() {
        showProgressLoading()
    }

    override fun hideLoading() {
        hideProgressLoading()
    }

    fun hideProgressLoading() {
     //   ProgressDialogHelper.cancel()
    }

    fun showProgressLoading() {
      //  ProgressDialogHelper.show(withContext())
    }

    override fun showToast(msg: String) {
        ToastHelper.showToast(withContext(), msg)
    }

    fun showToast(resId: Int) {
        ToastHelper.showToast(withContext(), withContext().getString(resId))
    }

    override fun showError(throwable: Throwable) {
        ToastHelper.showError(withContext(), throwable)
    }

}