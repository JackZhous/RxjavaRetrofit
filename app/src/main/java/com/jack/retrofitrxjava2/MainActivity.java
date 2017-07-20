package com.jack.retrofitrxjava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jack.retrofitrxjava2.manger.HttpManager;
import com.jack.retrofitrxjava2.response.TaskListResponse;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv_show);



    }

    public void onClickButton(View view){

        String param = "jqJJyn3G1T8o+GzKfuicOQ";
        HttpManager manager = HttpManager.Factory.getHttpManager();
        manager.getTaskList(param)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskListResponse value) {
                        tv.setText(value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        tv.setText("error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
