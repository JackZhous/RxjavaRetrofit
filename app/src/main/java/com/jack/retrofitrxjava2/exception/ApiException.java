package com.jack.retrofitrxjava2.exception;

/**
 * Created by jackzhous on 2017/4/21.
 */

public class ApiException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public ApiException(int code, String messgae){
        errorCode = code;
        errorMsg = messgae;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
