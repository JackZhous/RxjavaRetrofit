package com.jack.retrofitrxjava2.manger;


import com.jack.retrofitrxjava2.response.TaskListResponse;
import com.jackzhous.netlibrary.NetClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jackzhous on 2017/4/21.
 */

public interface HttpManager {

    String BASE_URL = "http://api.zhuanke.cn/api/lee/v1/";
    String COOKIE = "6814629f652726d3a96037625c5df007=hyvFs%2FCY9sERGYZ7RkAEy%2BzKgTAld1L95hrVZ96XuG4%3D";

    @FormUrlEncoded
    @POST("xianshi/list")
    Observable<TaskListResponse> getTaskList(@Field("param") String param);

    final class Factory{

        public static HttpManager getHttpManager(){
            NetClient client = new NetClient(){
                @Override
                public String getCookies() {
                    return COOKIE;
                }

                @Override
                public String getBaseUrl() {
                    return BASE_URL;
                }
            };

            HttpManager manager = client.getNetClient(HttpManager.class);
            return  manager;
        }
    }

}
