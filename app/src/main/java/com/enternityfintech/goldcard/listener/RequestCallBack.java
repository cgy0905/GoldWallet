package com.enternityfintech.goldcard.listener;

/**
 * Created by cgy
 * 2018/6/19  9:21
 */
public interface RequestCallBack<T> {

    /**
     * 请求之前调用
     */
    void beforeRequest();

    /**
     * 请求成功调用 显示数据
     * @param data
     */
    void success(T data);


    /**
     * 请求失败调用 展示错误信息
     * @param errorMsg
     */
    void onError(String errorMsg);
}
