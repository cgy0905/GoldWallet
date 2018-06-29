package com.enternityfintech.goldcard.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.MainActivity;
import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.activity.HoldActivity;
import com.enternityfintech.goldcard.ui.activity.TradeRecordActivity;
import com.enternityfintech.goldcard.ui.activity.TransferGoldActivity;
import com.enternityfintech.goldcard.ui.base.BaseFragment;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

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
    @BindView(R.id.rl_hold_standard)
    RelativeLayout rlHoldStandard;


    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initListener() {
        rlHoldStandard.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity
                (HoldActivity.class));
        llTurnGold.setOnClickListener(v -> ((MainActivity) getActivity()).jumpToActivity(TransferGoldActivity.class));
    }

    @OnClick(R.id.ll_record)
    void toTradeRecordPage() {
        startActivity(new Intent(getContext(), TradeRecordActivity.class));
    }
}
