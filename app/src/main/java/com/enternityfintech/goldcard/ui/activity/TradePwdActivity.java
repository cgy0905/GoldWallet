package com.enternityfintech.goldcard.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
    protected void initView() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_trade_pwd;
    }



    @OnClick({R.id.btn_remember, R.id.btn_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_remember:
                Intent intent = new Intent(TradePwdActivity.this, TradePwdVerifyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_forget:
                Intent intent1 = new Intent(TradePwdActivity.this, TradePwdSetActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
