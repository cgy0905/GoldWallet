package com.enternityfintech.goldcard.ui.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;

/**
 * Created by cgy
 * 2018/6/22  11:32
 */
public class AboutUsActivity extends BaseActivity{
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView() {
        TextView tvVersion = findViewById(R.id.tv_version);
        try {
            PackageInfo packageInfo = getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(getPackageName(), 0);
            tvVersion.setText("版本号V" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
