package com.jackzhous.netlibrary;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加自定义的cookie
 * Created by jackzhous on 2017/7/20.
 */

public class AddCookie implements Interceptor {

    private String cookie;

    public AddCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        builder.addHeader("Cookie", cookie);

        return chain.proceed(builder.build());
    }
}
