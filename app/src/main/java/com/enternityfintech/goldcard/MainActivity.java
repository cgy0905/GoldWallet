package com.enternityfintech.goldcard;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.enternityfintech.goldcard.business.mine.view.MineFragment;
import com.enternityfintech.goldcard.base.BaseActivity;
import com.enternityfintech.goldcard.business.main.view.HomeFragment;
import com.enternityfintech.goldcard.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {



    @BindView(R.id.flContent)
    FrameLayout flContent;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbMe)
    RadioButton rbMe;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    public void showHomeFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            fragmentTransaction.add(R.id.flContent, homeFragment);
        }
        commitShowFragment(fragmentTransaction, homeFragment);
    }

    @Override
    public void initView() {
        showHomeFragment();
    }

    public void showMineFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (mineFragment == null) {
            mineFragment = MineFragment.newInstance();
            fragmentTransaction.add(R.id.flContent, mineFragment);
        }
        commitShowFragment(fragmentTransaction, mineFragment);
    }

    private void commitShowFragment(FragmentTransaction transaction, Fragment fragment) {
        transaction.show(fragment);
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        hideFragment(transaction, homeFragment);
        hideFragment(transaction, mineFragment);
    }

    private void hideFragment(FragmentTransaction transaction, Fragment fragment) {
        if (fragment != null)
            transaction.hide(fragment);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                UIUtils.showToast(UIUtils.getString(R.string.exit_app));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.rbHome, R.id.rbMe})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rbHome:
                showHomeFragment();
                break;
            case R.id.rbMe:
                showMineFragment();
                break;
        }

    }
}
