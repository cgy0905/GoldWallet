package com.enternityfintech.goldcard.ui.fragment;

import android.os.Bundle;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseFragment;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

/**
 * Created by cgy
 * 2018/6/14  17:39
 */
public class HomeFragment extends BaseFragment{

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
}
