package com.jack.retrofitrxjava2;

import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Rfc822Token;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jack.retrofitrxjava2.bean.LoginBean;
import com.jack.retrofitrxjava2.bean.UserBean;
import com.jack.retrofitrxjava2.exception.ApiException;
import com.jack.retrofitrxjava2.manger.HttpManager;
import com.jack.retrofitrxjava2.manger.LoadDialogContr;
import com.jack.retrofitrxjava2.request.HttpRequest;
import com.jack.retrofitrxjava2.util.JLog;
import com.jack.retrofitrxjava2.view.LoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_show)
    TextView textView;
    private Disposable disposable;
    private LoadDialogContr dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dialog = new LoadDialogContr(this);
    }

    @OnClick(R.id.btn)
    public void onClick(View view){
        HttpRequest request = new HttpRequest();
        request.setCmd("login");
        LoginBean bean = new LoginBean();
        bean.setMobile("18200130442");
        bean.setPassword("21218cca77804d2ba1922c33e0151105");
        request.setParams(bean);
        HttpManager.getInstance().getLogin(observer, request);

      //  textView.setText("you click button");


    }






    private Observer observer = new Observer<UserBean>() {
        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
            JLog.i("j_net", "onSubscribe");
            dialog.show();
        }

        @Override
        public void onNext(UserBean userBean) {
            JLog.i("j_net", new Gson().toJson(userBean));

        }

        @Override
        public void onError(Throwable e) {
            dialog.hide();
            if(e instanceof ApiException){
                ApiException apiException = (ApiException)e;
                JLog.i("j_net", "onError" + apiException.getErrorMsg());
            }

            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            disposable = null;
            dialog.hide();
            JLog.i("j_net", "onComplete");
        }
    };




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable != null){
            disposable.dispose();
        }
    }
}
