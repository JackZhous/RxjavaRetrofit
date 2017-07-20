package com.jackzhous.netlibrary;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 用于网络中核心控制模块
 * Created by jackzhous on 2017/7/20.
 */

public abstract class NetClient {


    private static final int CINNECT_TIME = 5;



    public abstract String getCookies();


    public abstract String getBaseUrl();


    /**
     * 获取网络控制的客户端
     * @return
     */
    public <T>T getNetClient(Class<T> tClass){

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                .connectTimeout(CINNECT_TIME, TimeUnit.SECONDS);

        String baseurl = getBaseUrl();
        if(TextUtils.isEmpty(baseurl)){
            throw new RuntimeException("retrofit need a base url");
        }

        AddCookie addCookie = addCookie();
        if(addCookie != null){
            okBuilder.addInterceptor(addCookie);
        }

        Retrofit Retrofit = new Retrofit.Builder().client(okBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseurl)
                .build();

        T t = Retrofit.create(tClass);
        return  t;
    }

    /**
     * 添加cokkie
     */
    private AddCookie addCookie(){
        String cookie = getCookies();
        if(TextUtils.isEmpty(cookie)){
            return null;
        }

        AddCookie addCookie = new AddCookie(cookie);

        return addCookie;
    }

}
