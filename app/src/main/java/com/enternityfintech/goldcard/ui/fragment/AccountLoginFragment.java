package com.enternityfintech.goldcard.ui.fragment;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseFragment;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

/**
 * Created by cgy
 * 2018/6/20  14:46
 */
public class AccountLoginFragment extends BaseFragment{
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_account_login;
    }
}
