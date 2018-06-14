package com.enternityfintech.goldcard.ui.base;

/**
 * Created by cgy
 * 2018/6/14  15:02
 */
public interface BaseView {

    void showProgress();

    void onCompleted();

    void onError(Throwable e);
}
