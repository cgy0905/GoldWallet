package com.enternityfintech.goldcard.app.base.mvp

/**
 * Created by cool on 2018/2/28.
 */
abstract class Presenter<V : IView>(var view: V) : IPresenter