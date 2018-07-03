package com.enternityfintech.goldcard.business.capture.presenter

import com.enternityfintech.goldcard.app.base.mvp.ILoadView
import com.enternityfintech.goldcard.app.base.mvp.Presenter

/**
 *Created by cool on 2018/7/2
 */
class CapturePresenter(view: ILoadView) : Presenter<ILoadView>(view) {

    fun decode(result: String) {
        //TODO
        view.showToast(result)
    }
}