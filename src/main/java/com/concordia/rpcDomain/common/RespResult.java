package com.concordia.rpcDomain.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RespResult<T> implements Serializable {

    private static final long serialVersionUID = -6571343353689776688L;

    int code;

    String message;

    T data;

    public RespResult(ResultCode resultCode) {
        this.code = resultCode.code;
        this.message = resultCode.message;
    }

    public RespResult(ResultCode resultCode, T data) {
        this.code = resultCode.code;
        this.message = resultCode.message;
        this.data = data;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
