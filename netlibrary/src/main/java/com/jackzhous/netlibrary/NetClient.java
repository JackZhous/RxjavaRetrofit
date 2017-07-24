package com.jackzhous.netlibrary;

import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 用于网络中核心控制模块
 * Created by jackzhous on 2017/7/20.
 */

public class NetClient {


    private static final int CINNECT_TIME = 5;

    private static OkHttpClient.Builder okBuilder  = new OkHttpClient.Builder()
            .connectTimeout(CINNECT_TIME, TimeUnit.SECONDS);;

    private String baseUrl;

    public NetClient(String baseUrl){
        if(TextUtils.isEmpty(baseUrl)){
            throw new RuntimeException("retrofit need a base url");
        }

        this.baseUrl = baseUrl;
    }


    /**
     * 获取网络控制的客户端
     * @return
     */
    public <T>T getNetClient(Class<T> tClass){


        Retrofit Retrofit = new Retrofit.Builder().client(okBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        T t = Retrofit.create(tClass);
        return  t;
    }


    public static void addHeader(final String key, final String value){

        okBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header(key, value);
                Request request = requestBuilder.build();
                return chain.proceed(request);

            }
        });
    }


    public static void removeHeader(final String key){
        okBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .removeHeader(key);
                Request request = requestBuilder.build();
                return chain.proceed(request);

            }
        });
    }

}
