package com.enternityfintech.goldcard.model.response;

/**
 * Created by cgy
 * 2018/6/19  14:43
 */
public class SendCodeResponse {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
