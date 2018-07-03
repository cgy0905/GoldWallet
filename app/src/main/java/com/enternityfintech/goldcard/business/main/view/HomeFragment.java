package com.enternityfintech.goldcard.business.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseFragment;
import com.enternityfintech.goldcard.business.trade.view.TradeRecordActivity;
import com.enternityfintech.goldcard.business.trade.view.TransferGoldActivity;
import com.enternityfintech.goldcard.ui.activity.HoldActivity;

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
    @BindView(R.id.ll_turnGold)
    LinearLayout llTurnGold;
    @BindView(R.id.ll_record)
    LinearLayout llRecord;
    @BindView(R.id.rl_possess_standard)
    RelativeLayout rlPossessStandard;


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



    @OnClick(R.id.ll_turnGold)
    void toTurnGoldPage(){
        startActivity(new Intent(getContext(), TransferGoldActivity.class));
    }

    @OnClick(R.id.ll_record)
    void toTradeRecordPage() {
        startActivity(new Intent(getContext(), TradeRecordActivity.class));
    }

    @OnClick(R.id.rl_possess_standard)
    void toPossessPage() {
        startActivity(new Intent(getContext(), HoldActivity.class));
    }

}
