package com.enternityfintech.goldcard.base;

import com.enternityfintech.goldcard.business.login.view.LoginFragment;
import com.enternityfintech.goldcard.business.login.view.RegisterFragment;
import com.enternityfintech.goldcard.business.main.view.HomeFragment;
import com.enternityfintech.goldcard.business.mine.view.MineFragment;

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
    public RegisterFragment registerFragment;
    public LoginFragment loginFragment;


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


    public RegisterFragment getRegisterFragment() {
        if (registerFragment == null) {
            synchronized (FragmentFactory.class) {
                if (registerFragment == null) {
                    registerFragment = new RegisterFragment();
                }
            }
        }
        return registerFragment;
    }

    public LoginFragment getLoginFragment() {
        if (loginFragment == null) {
            synchronized (FragmentFactory.class) {
                if (loginFragment == null) {
                    loginFragment = new LoginFragment();
                }
            }
        }
        return loginFragment;
    }
}
