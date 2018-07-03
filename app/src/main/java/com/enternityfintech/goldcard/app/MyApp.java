package com.enternityfintech.goldcard.app;

import com.enternityfintech.goldcard.app.base.BaseApp;

/**
 * Created by cgy
 * 2018/6/14  10:58
 * BaseApp的拓展，用于设置其他第三方的初始化
 */
public class MyApp extends BaseApp{

    private static MyApp INSTANCE;

    public static synchronized MyApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        /*==============相关第三方sdk的初始化等操作===============*/
    }
}
