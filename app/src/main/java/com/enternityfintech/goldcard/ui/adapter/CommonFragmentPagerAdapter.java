package com.enternityfintech.goldcard.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.enternityfintech.goldcard.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by cgy
 * 2018/6/26  16:30
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    public static int MAIN_VIEW_PAGER = 1;//主界面的ViewPager

    private int mViewPagerType = 0;

    public String[] mainViewPagerTitle = null;
    private List<BaseFragment> fragments;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragments = fragmentList;
    }


    //根据传入的viewPagerType，在getTitle中返回不同的标题信息
    public CommonFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, int viewPagerType) {
        this(fm, fragments);
        mViewPagerType = viewPagerType;
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
