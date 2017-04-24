package com.jack.retrofitrxjava2.manger;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.jack.retrofitrxjava2.util.JLog;
import com.jack.retrofitrxjava2.view.LoadDialog;

/**
 * Created by jackzhous on 2017/4/24.
 */

public class LoadDialogContr {
    private static final String TAG = "j_dialog";

    public static Handler handler = new Handler(Looper.getMainLooper());

    private LoadDialog      loadDialog;

    private boolean         isNetEnd = false;

    private Activity        mActivity;

    public LoadDialogContr(Activity activity){
        mActivity = activity;
    }

    public void show(){
        handler.postDelayed(delayRunable, 1000);
    }



    private Runnable delayRunable = new Runnable() {
        @Override
        public void run() {
            JLog.i(TAG, "running");
            if(!isNetEnd){
                JLog.i(TAG, "showing");
                loadDialog = new LoadDialog(mActivity, true, null);
                loadDialog.show();
            }

        }
    };


    public void hide(){
        isNetEnd = true;
       // mActivity = null;
        if(null != loadDialog){
            JLog.i(TAG, "hideing");
            loadDialog.dismiss();
        }
    }


}
