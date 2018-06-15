package com.enternityfintech.goldcard.ui.view;

/**
 * Created by cgy
 * 2018/6/15  10:51
 */
public interface ILoginView {

    void showLoading();

    void hideLoading();

    void showUserNameError(String error);

    void showPassWordError(String error);

    void loginSuccess();
}
