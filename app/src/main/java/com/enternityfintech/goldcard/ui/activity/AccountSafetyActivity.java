package com.enternityfintech.goldcard.ui.activity;

import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/21  16:05
 */
public class AccountSafetyActivity extends BaseActivity {
    @BindView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @BindView(R.id.tv_trade_pwd)
    TextView tvTradePwd;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_account_safety;
    }


    @Override
    public void initListener() {
        tvLoginPwd.setOnClickListener(v -> jumpToActivity(ModifyLoginPwdActivity.class));
        tvTradePwd.setOnClickListener(v -> jumpToActivity(TradePwdActivity.class));
    }
}
