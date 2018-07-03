package com.enternityfintech.goldcard.model.cache;

import com.enternityfintech.goldcard.utils.AppConst;
import com.enternityfintech.goldcard.utils.SPUtils;
import com.enternityfintech.goldcard.utils.UIUtils;

/**
 * Created by cgy
 * 2018/6/15  15:03
 * 用户缓存
 */
public class UserCache {

    public static String getId() {
        return SPUtils.getInstance(UIUtils.getContext()).getString(AppConst.User.ID, "");
    }

    public static String getPhone() {
        return SPUtils.getInstance(UIUtils.getContext()).getString(AppConst.User.PHONE, "");
    }

    public static String getToken() {
        return SPUtils.getInstance(UIUtils.getContext()).getString(AppConst.User.TOKEN, "");
    }

    public static void save(String id, String account, String token) {
        SPUtils.getInstance(UIUtils.getContext()).putString(AppConst.User.ID, id);
        SPUtils.getInstance(UIUtils.getContext()).putString(AppConst.User.PHONE, account);
        SPUtils.getInstance(UIUtils.getContext()).putString(AppConst.User.TOKEN, token);
    }

    public static void clear() {
        SPUtils.getInstance(UIUtils.getContext()).remove(AppConst.User.ID);
        SPUtils.getInstance(UIUtils.getContext()).remove(AppConst.User.PHONE);
        SPUtils.getInstance(UIUtils.getContext()).remove(AppConst.User.TOKEN);
//        DBManager.getInstance().deleteAllUserInfo();
    }

}
