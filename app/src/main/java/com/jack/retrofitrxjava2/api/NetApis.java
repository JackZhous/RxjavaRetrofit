package com.jack.retrofitrxjava2.api;

import com.jack.retrofitrxjava2.bean.LoginBean;
import com.jack.retrofitrxjava2.bean.UserBean;
import com.jack.retrofitrxjava2.request.HttpRequest;
import com.jack.retrofitrxjava2.response.HttpResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jackzhous on 2017/4/21.
 */

public interface NetApis {

    @POST("app")
    Observable<HttpResponse<UserBean>> login(@Body HttpRequest<LoginBean> beanHttpRequest);

}
