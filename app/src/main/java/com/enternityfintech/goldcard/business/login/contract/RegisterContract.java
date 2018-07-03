package com.enternityfintech.goldcard.business.login.contract;


import com.enternityfintech.goldcard.base.BasePresenter;
import com.enternityfintech.goldcard.base.BaseView;

/**
 * Created by cgy
 * 2018/7/2  14:14
 */
public interface RegisterContract {

    interface IRegisterView extends BaseView {
        void registerSuccess();
    }


    interface RegisterPresenter extends BasePresenter<IRegisterView> {
        void register(String verifyCode, String phone, String password);
    }
}
