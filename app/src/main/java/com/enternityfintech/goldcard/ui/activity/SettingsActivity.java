package com.enternityfintech.goldcard.ui.activity;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

/**
 * Created by cgy
 * 2018/6/22  11:09
 */
public class SettingsActivity extends BaseActivity{
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_settings;
    }
}
