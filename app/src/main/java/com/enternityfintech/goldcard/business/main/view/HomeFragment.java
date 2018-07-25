package com.enternityfintech.goldcard.business.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseFragment;
import com.enternityfintech.goldcard.business.trade.view.TradeRecordActivity;
import com.enternityfintech.goldcard.business.trade.view.TransferGoldActivity;
import com.enternityfintech.goldcard.ui.activity.PossessActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cgy
 * 2018/6/14  17:39
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tv_gold_price)
    TextView tvGoldPrice;
    @BindView(R.id.tv_coins)
    TextView tvCoins;
    @BindView(R.id.tv_amounts)
    TextView tvAmounts;
    @BindView(R.id.fl_turnGold)
    FrameLayout flTurnGold;
    @BindView(R.id.fl_record)
    FrameLayout flRecord;

    @BindView(R.id.rl_hold_standard)
    RelativeLayout rlHoldStandard;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }





    @OnClick(R.id.fl_turnGold)
    void toTurnGoldPage(){
        startActivity(new Intent(getContext(), TransferGoldActivity.class));
    }

    @OnClick(R.id.fl_record)
    void toTradeRecordPage() {
        startActivity(new Intent(getContext(), TradeRecordActivity.class));
    }

    @OnClick(R.id.rl_hold_standard)
    void toPossessPage() {
        startActivity(new Intent(getContext(), PossessActivity.class));
    }

}
