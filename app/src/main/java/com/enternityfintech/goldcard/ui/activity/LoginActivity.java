package com.enternityfintech.goldcard.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.adapter.TabViewPagerAdapter;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BasePresenter;
import com.enternityfintech.goldcard.ui.fragment.HomeFragment;
import com.enternityfintech.goldcard.ui.fragment.LoginFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.login_tabs)
    TabLayout loginTabs;
    @Bind(R.id.login_viewpager)
    ViewPager login_viewpager;

    ArrayList<Fragment> fragmentList = new ArrayList<>();



    @Override
    public void init() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void initData() {
        //初始化viewpager的数据
        String[] titles = getResources().getStringArray(R.array.tab_names);
        fragmentList.add(new LoginFragment());
        fragmentList.add(new HomeFragment());
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        login_viewpager.setAdapter(adapter);
        loginTabs.setupWithViewPager(login_viewpager);

    }

    //设置tab下的viewpager
//    private void setupViewPager() {
//        setupViewPager(login_viewpager);
//        loginTabs.setupWithViewPager(login_viewpager);
//        loginTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                login_viewpager.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }

//    private void setupViewPager(ViewPager viewpager) {
//        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFrag(new FragmentLogin(), "登录");
//        adapter.addFrag(new FragmentRegist(), "注册");
//        viewPager.setAdapter(adapter);
//    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }


}
