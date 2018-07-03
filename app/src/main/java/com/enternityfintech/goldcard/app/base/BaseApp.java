package com.enternityfintech.goldcard.app.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by cgy
 * 2018/7/3  13:40
 */


public class BaseApp extends MultiDexApplication {

    //以下属性应用于真个应用程序，合理利用资源，减少资源浪费
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
