package com.enternityfintech.goldcard.business.login.contract;

import com.enternityfintech.goldcard.base.BasePresenter;
import com.enternityfintech.goldcard.base.BaseView;

/**
 * Created by cgy
 * 2018/7/2  13:47
 */
public interface LoginContract {

    interface ILoginView extends BaseView {
        void loginSuccess();
    }

    interface ILoginPresenter extends BasePresenter<ILoginView> {
        void login(String username, String password);
    }
}
