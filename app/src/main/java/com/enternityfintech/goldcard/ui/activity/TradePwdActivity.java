package com.enternityfintech.goldcard.ui.activity;

import android.widget.Button;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/21  17:07
 */
public class TradePwdActivity extends BaseActivity {
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.btn_remember)
    Button btnRemember;
    @BindView(R.id.btn_forget)
    Button btnForget;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_trade_pwd;
    }

    @Override
    public void initListener() {
        btnRemember.setOnClickListener(v -> jumpToActivity(TradePwdVerifyActivity.class));
        btnForget.setOnClickListener(v -> jumpToActivity(TradePwdSetActivity.class));
    }
}
