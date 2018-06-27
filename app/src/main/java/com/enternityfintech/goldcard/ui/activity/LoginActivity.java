package com.enternityfintech.goldcard.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.androidkun.xtablayout.XTabLayout;
import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.ui.base.BaseActivity;
import com.enternityfintech.goldcard.ui.base.BaseFragment;
import com.enternityfintech.goldcard.ui.base.BaseFragmentAdapter;
import com.enternityfintech.goldcard.ui.base.BasePresenter;
import com.enternityfintech.goldcard.ui.fragment.AccountLoginFragment;
import com.enternityfintech.goldcard.ui.fragment.FastLoginFragment;
import com.enternityfintech.goldcard.widget.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {



    List<BaseFragment> fragmentList = new ArrayList<>();
    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.login_tabs)
    XTabLayout loginTabs;
    @BindView(R.id.login_viewpager)
    ViewPager loginViewpager;
    private String[] titles;


    @Override
    public void init() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void initData() {
        //初始化viewpager的数据
        titles = getResources().getStringArray(R.array.tab_names);
        fragmentList.add(new AccountLoginFragment());
        fragmentList.add(new FastLoginFragment());
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        loginViewpager.setAdapter(adapter);
        loginTabs.setupWithViewPager(loginViewpager);

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
