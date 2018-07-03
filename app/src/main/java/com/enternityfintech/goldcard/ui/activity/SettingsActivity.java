package com.enternityfintech.goldcard.ui.activity;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by cgy
 * 2018/6/22  11:09
 */
public class SettingsActivity extends BaseActivity {
    @BindView(R.id.rl_menu_cache)
    RelativeLayout rlMenuCache;
    @BindView(R.id.tv_contact_us)
    TextView tvContactUs;
    @BindView(R.id.tv_hotLine)
    TextView tvHotLine;



    @Override
    protected void initView() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_settings;
    }


}
