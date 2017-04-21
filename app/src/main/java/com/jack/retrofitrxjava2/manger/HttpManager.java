package com.jack.retrofitrxjava2.manger;

import com.jack.retrofitrxjava2.api.NetApis;
import com.jack.retrofitrxjava2.bean.UserBean;
import com.jack.retrofitrxjava2.exception.ApiException;
import com.jack.retrofitrxjava2.request.HttpRequest;
import com.jack.retrofitrxjava2.response.HttpResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jackzhous on 2017/4/21.
 */

public class HttpManager {
    private static final String BASE_URL = "http://117.139.247.132:6080/box/";

    private static final int CINNECT_TIME = 5;

    private Retrofit         mRetrofit;

    private NetApis          mNetApis;

    private static final int  SUCCESS = 1;

    private HttpManager(){
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                                                         .connectTimeout(CINNECT_TIME, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder().client(okBuilder.build())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                            .baseUrl(BASE_URL)
                                            .build();

        mNetApis  = mRetrofit.create(NetApis.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpManager INSTANCE = new HttpManager();
    }

    //获取单例
    public static HttpManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void getLogin(Observer<UserBean> observer, HttpRequest request){
        Observable observable = mNetApis.login(request).map(new HttpResultFunc());

        toSubscribe(observable, observer);
    }


    private <T> void toSubscribe(Observable<T> o, Observer<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Function<HttpResponse<T>, T> {

        @Override
        public T apply(@NonNull HttpResponse<T> tHttpResponse) throws Exception {

            if(SUCCESS != tHttpResponse.getCode()){
                throw new ApiException(tHttpResponse.getCode(), tHttpResponse.getMessage());
            }

            return tHttpResponse.getDetail();
        }
    }
}
