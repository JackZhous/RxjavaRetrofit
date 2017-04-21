package com.jack.retrofitrxjava2.response;

/**
 * Created by jackzhous on 2017/4/21.
 */

public class HttpResponse<T> {
    private int code;
    private String message;

    private T detail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }
}
