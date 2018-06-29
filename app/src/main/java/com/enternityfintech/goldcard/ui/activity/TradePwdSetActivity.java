package com.enternityfintech.goldcard.ui.activity;

import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;
import com.enternityfintech.goldcard.widget.CodeView;
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
    CodeView pwdView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_trade_pwd_settings;
    }


    @Override
    public void initListener() {
        pwdView.setListener(new CodeView.Listener() {
            @Override
            public void onValueChanged(String value) {

            }

            @Override
            public void onComplete(String value) {
                jumpToActivity(TradePwdSetAgainActivity.class);
            }
        });
    }
}
