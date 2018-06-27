package com.enternityfintech.goldcard.model.exception;

import com.enternityfintech.goldcard.R;
import com.enternityfintech.goldcard.utils.UIUtils;

/**
 * Created by cgy
 * 2018/6/19  14:47
 * 服务器异常
 */
public class ServerException extends Exception{


    public ServerException(int errorCode) {
        this(UIUtils.getString(R.string.error_code) + errorCode);
    }
    public ServerException(String message) {
        super(message);
    }
}
