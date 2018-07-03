package com.enternityfintech.goldcard.ui.activity;

import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseActivity;
import com.enternityfintech.goldcard.widget.PasswordInputView;
import com.enternityfintech.goldcard.widget.TitleView;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/22  9:02
 */
public class TradePwdSetActivity extends BaseActivity {
    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.pwd_view)
    PasswordInputView pwdView;


    @Override
    protected void initView() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_trade_pwd_settings;
    }



}
