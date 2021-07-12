package com.concordia.component.exception;

import com.concordia.entity.rpcDomain.common.ResultCode;

public class ValidateException extends RuntimeException{

    private ResultCode resultCode;
    private String msg;

    public ValidateException(ResultCode resultCode, String msg) {
        this.resultCode = resultCode;
        this.msg = msg;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
