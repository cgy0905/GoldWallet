package com.enternityfintech.goldcard;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;
import com.enternityfintech.goldcard.ui.fragment.FragmentFactory;
import com.enternityfintech.goldcard.ui.fragment.HomeFragment;
import com.enternityfintech.goldcard.ui.fragment.MineFragment;
import com.enternityfintech.goldcard.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.flContent)
    FrameLayout flContent;
    @Bind(R.id.rbHome)
    RadioButton rbHome;
    @Bind(R.id.rbMe)
    RadioButton rbMe;


    private HomeFragment homeFragment;

    private MineFragment mineFragment;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    public void showHomeFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (homeFragment == null) {
            homeFragment = FragmentFactory.getInstance().getHomeFragment();
            fragmentTransaction.add(R.id.flContent, homeFragment);
        }
        commitShowFragment(fragmentTransaction, homeFragment);
    }

    public void showMineFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (mineFragment == null) {
            mineFragment = FragmentFactory.getInstance().getMineFragment();
            fragmentTransaction.add(R.id.flContent, mineFragment);
        }
        commitShowFragment(fragmentTransaction, homeFragment);
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

    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
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
