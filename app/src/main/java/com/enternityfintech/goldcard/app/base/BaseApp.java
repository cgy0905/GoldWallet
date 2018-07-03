package com.enternityfintech.goldcard.app.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by cgy
 * 2018/7/3  13:40
 */
public class BaseApp extends MultiDexApplication{


    private static Context context;//上下文
    @Override
    public void onCreate() {
        super.onCreate();

        //对全局属性赋值
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context mContext) {
        BaseApp.context = mContext;
    }
}
