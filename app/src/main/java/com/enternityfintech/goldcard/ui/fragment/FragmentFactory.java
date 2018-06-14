package com.enternityfintech.goldcard.ui.fragment;

/**
 * Created by cgy
 * 2018/6/14  17:36
 * Fragment工厂类
 */
public class FragmentFactory {

    static FragmentFactory mInstance;
    private FragmentFactory(){

    }

    public static FragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (FragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new FragmentFactory();
                }
            }
        }
        return mInstance;
    }

    public HomeFragment homeFragment;
    public MineFragment mineFragment;


    public HomeFragment getHomeFragment() {
        if (homeFragment == null) {
            synchronized (FragmentFactory.class) {
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
            }
        }
        return homeFragment;
    }

    public MineFragment getMineFragment() {
        if (mineFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
            }
        }
        return mineFragment;
    }
}
