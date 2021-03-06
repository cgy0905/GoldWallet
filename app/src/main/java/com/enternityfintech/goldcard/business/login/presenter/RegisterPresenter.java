package com.enternityfintech.goldcard.business.login.presenter;


import com.enternityfintech.goldcard.business.login.contract.RegisterContract;
import com.enternityfintech.goldcard.utils.LogUtils;
import com.enternityfintech.goldcard.utils.UIUtils;

import java.util.Timer;

import rx.Subscription;

/**
 * Created by cgy
 * 2018/6/19  11:22
 */
public class RegisterPresenter implements RegisterContract.RegisterPresenter {

    int time = 0;
    private Timer timer;
    private Subscription subscription;

    private RegisterContract.IRegisterView registerView;
    public RegisterPresenter(RegisterContract.IRegisterView registerView) {
        this.registerView = registerView;
    }


//    public RegisterPresenter(BaseActivity context) {
//        super(context);
//    }

//    public void sendCode() {
//        //String phone = getView().getEtPhone().getText().toString().trim();
//
//        if (TextUtils.isEmpty(phone)) {
//            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
//            return;
//        }
//
//        if (!RegularUtils.isMobile(phone)) {
//            UIUtils.showToast(UIUtils.getString(R.string.phone_format_error));
//            return;
//        }
//        //mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
//        ApiRetrofit.getInstance().checkPhoneAvailable(phone)
//                .subscribeOn(Schedulers.io())
//                .flatMap((Func1<CheckPhoneResponse, Observable<SendCodeResponse>>) checkPhoneResponse -> {
//                    int code = checkPhoneResponse.getCode();
//                    if (code == 200) {
//                        return ApiRetrofit.getInstance().sendCode(phone);
//                    } else {
//                        return Observable.error(new ServerException(UIUtils.getString(R.string.phone_not_available)));
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(sendCodeResponse -> {
//            //mContext.hideWaitingDialog();
//            int code = sendCodeResponse.getCode();
//            if (code == 200) {
//                changeSendCodeBtn();
//            } else {
//                sendCodeError(new ServerException(UIUtils.getString(R.string.send_code_error)));
//            }
//        }, this::sendCodeError);
//
//    }

    private void sendCodeError(Throwable throwable) {
        //mContext.hideWaitingDialog();
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
    }

    private void changeSendCodeBtn() {
//        //开始一分钟倒计时
//        //每一秒执行一次Task
//        subscription = Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
//            time = 60;
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    subscriber.onNext(--time);
//                }
//            };
//            timer = new Timer();
//            timer.schedule(task,0, 1000); //每一秒执行一次task
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(time -> {
//                    if (getView().getBtnSendCode() != null) {
//                        if (time >= 0) {
//                            getView().getBtnSendCode().setEnabled(false);
//                            getView().getBtnSendCode().setText(time + "");
//                        } else {
//                            getView().getBtnSendCode().setEnabled(true);
//                            getView().getBtnSendCode().setText(UIUtils.getString(R.string.send_code_btn_normal_tip));
//                        }
//                    } else {
//                        timer.cancel();
//                    }
//                }, throwable -> LogUtils.sf(throwable.getLocalizedMessage()));
    }

    public void register() {
//        String phone = getView().getEtPhone().getText().toString().trim();
//        String password = getView().getEtPwd().getText().toString().trim();
//        String code = getView().getEtVerifyCode().getText().toString().trim();
//
//
//        if (TextUtils.isEmpty(phone)) {
//            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
//            return;
//        }
//
//        if (TextUtils.isEmpty(code)) {
//            UIUtils.showToast(UIUtils.getString(R.string.vertify_code_not_empty));
//            return;
//        }

    }

    @Override
    public void register(String verifyCode, String phone, String password) {

    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
