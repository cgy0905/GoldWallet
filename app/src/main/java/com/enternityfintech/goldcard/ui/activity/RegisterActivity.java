package com.enternityfintech.goldcard.ui.activity;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.business.login.contract.RegisterContract;
import com.enternityfintech.goldcard.business.login.presenter.RegisterPresenter;
import com.enternityfintech.goldcard.base.BaseActivity;

/**
 * Created by cgy
 * 2018/6/20  15:20
 */
public class RegisterActivity extends BaseActivity implements RegisterContract.IRegisterView{

    private RegisterContract.RegisterPresenter registerPresenter;

    @Override
    protected void initView() {
       registerPresenter = new RegisterPresenter(this);

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public void registerSuccess() {

    }
}
