package com.enternityfintech.goldcard.ui.fragment;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseFragment;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

/**
 * Created by cgy
 * 2018/6/20  15:53
 */
public class ResetPasswordFragment extends BaseFragment{
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_reset_password;
    }
}
