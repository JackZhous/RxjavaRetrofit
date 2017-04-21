package com.jack.retrofitrxjava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.jack.retrofitrxjava2.bean.LoginBean;
import com.jack.retrofitrxjava2.bean.UserBean;
import com.jack.retrofitrxjava2.manger.HttpManager;
import com.jack.retrofitrxjava2.request.HttpRequest;
import com.jack.retrofitrxjava2.util.JLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private Observer observer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view){
        HttpRequest request = new HttpRequest();
        request.setCmd("login");
        LoginBean bean = new LoginBean();
        bean.setMobile("18200130442");
        bean.setPassword("21218cca77804d2ba1922c33e0151105");
        request.setParams(bean);

        observer = new Observer<UserBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                JLog.i("j_net", "onSubscribe");
            }

            @Override
            public void onNext(UserBean userBean) {
                JLog.i("j_net", new Gson().toJson(userBean));
            }

            @Override
            public void onError(Throwable e) {
                JLog.i("j_net", "onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                JLog.i("j_net", "onComplete");
            }
        };

        HttpManager.getInstance().getLogin(observer, request);

    }


}
