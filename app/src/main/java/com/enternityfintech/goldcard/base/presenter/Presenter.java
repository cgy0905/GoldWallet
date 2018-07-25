package com.enternityfintech.goldcard.base.presenter;

import com.enternityfintech.goldcard.base.BaseView;

import org.jetbrains.annotations.NotNull;

/**
 * Created by cgy
 * 2018/7/4  9:29
 */
public interface Presenter<T extends BaseView> {

    void attachView(@NotNull T View);

    void detachView();
}
