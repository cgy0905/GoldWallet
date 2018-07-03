package com.enternityfintech.goldcard.base;


import org.jetbrains.annotations.NotNull;

/**
 * Created by cgy
 * 2018/6/14  14:22
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(@NotNull T view);

    void detachView();
}
