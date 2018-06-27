package com.enternityfintech.goldcard.api;

import com.enternityfintech.goldcard.api.base.BaseApiRetrofit;
import com.enternityfintech.goldcard.model.request.LoginRequest;
import com.enternityfintech.goldcard.model.response.CheckPhoneResponse;
import com.enternityfintech.goldcard.model.response.LoginResponse;
import com.enternityfintech.goldcard.model.response.SendCodeResponse;
import com.enternityfintech.goldcard.model.response.VerifyCodeResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by cgy
 * 2018/6/14  14:02
 */
public class ApiRetrofit extends BaseApiRetrofit{

    public MyApi mApi;
    public static ApiRetrofit mInstance;

    private ApiRetrofit() {
        super();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //在构造方法中完成对Retrofit接口的初始化
        mApi = new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MyApi.class);
    }


    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }
    private RequestBody getRequestBody(Object obj) {
        String route = new Gson().toJson(obj);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), route);
        return body;
    }

    public Observable<LoginResponse> login(String region, String phone, String password) {
        return mApi.login(getRequestBody(new LoginRequest(region, phone, password)));
    }

    public Observable<CheckPhoneResponse> checkPhoneAvailable(String phone) {
        return null;
    }

    public Observable<VerifyCodeResponse> verifyCode(String phone, String code) {
        return null;
    }

    public Observable<SendCodeResponse> sendCode(String phone) {
        return null;
    }
}
