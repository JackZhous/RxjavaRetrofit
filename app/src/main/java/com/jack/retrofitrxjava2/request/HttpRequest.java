package com.jack.retrofitrxjava2.request;

/**
 * Created by jackzhous on 2017/4/21.
 */

public class HttpRequest<T> {

    private String cmd;

    private T params;


    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
