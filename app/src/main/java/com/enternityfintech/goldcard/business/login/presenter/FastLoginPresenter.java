package com.enternityfintech.goldcard.business.login.presenter;

import android.text.TextUtils;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.business.login.contract.LoginContract;
import com.enternityfintech.goldcard.model.cache.UserCache;
import com.enternityfintech.goldcard.net.ApiRetrofit;
import com.enternityfintech.goldcard.utils.AppConst;
import com.enternityfintech.goldcard.utils.LogUtils;
import com.enternityfintech.goldcard.utils.RegularUtils;
import com.enternityfintech.goldcard.utils.UIUtils;

import org.jetbrains.annotations.NotNull;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cgy
 * 2018/6/15  10:52
 */
public class LoginPresenter implements LoginContract.ILoginPresenter {

    private LoginContract.ILoginView loginView;
    private Subscription subscription;
    
    public void login(String phone, String password) {
        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
            return;
        }
        if (RegularUtils.isMobile(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.incorrect_phone_format));
            return;
        }


        ApiRetrofit.getInstance().login(AppConst.REGION, phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                   int code = loginResponse.getCode();
                   if (code == 200) {
                       UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());

                   }
                }, this::loginError);

    }

    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
    }

    public void sendCode() {

    }


    @Override
    public void attachView(@NotNull LoginContract.ILoginView view) {
        loginView = view;
    }




    @Override
    public void detachView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        loginView = null;
    }
}
