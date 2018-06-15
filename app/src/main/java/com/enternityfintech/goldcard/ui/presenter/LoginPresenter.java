package com.enternityfintech.goldcard.ui.presenter;

import android.text.TextUtils;

import com.enternityfintech.goldcard.MainActivity;
import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.api.ApiRetrofit;
import com.enternityfintech.goldcard.app.AppConst;
import com.enternityfintech.goldcard.model.cache.UserCache;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;
import com.enternityfintech.goldcard.ui.view.ILoginView;
import com.enternityfintech.goldcard.utils.LogUtils;
import com.enternityfintech.goldcard.utils.UIUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cgy
 * 2018/6/15  10:52
 */
public class LoginPresenter extends BasePresenter<ILoginView>{
    public LoginPresenter(BaseActivity context) {
        super(context);
    }

    public void login(String phone, String password) {
        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            return;
        }
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        ApiRetrofit.getInstance().login(AppConst.REGION, phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                   int code = loginResponse.getCode();
                   if (code == 200) {
                       UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
                       mContext.jumpToActivityAndClearTask(MainActivity.class);
                       mContext.finish();
                   }
                }, this::loginError);

    }

    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        mContext.hideWaitingDialog();
    }
}
