package com.enternityfintech.goldcard.app;

import com.enternityfintech.goldcard.utils.LogUtils;

/**
 * Created by cgy
 * 2018/6/14  11:01
 * 全局常量管理类
 */
public class AppConst {

    public static final String TAG = "Gold_Card";
    public static final int DEBUGLEVEL = LogUtils.LEVEL_ALL;//日志输出级别

    public static final String REGION = "86";



    public static final class User {
        public static final String ID = "id";
        public static final String PHONE = "phone";
        //        public static final String ACCOUNT = "account";
        public static final String TOKEN = "token";
    }
}
