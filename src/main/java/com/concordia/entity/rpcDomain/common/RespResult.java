package com.concordia.entity.rpcDomain.common;

import java.io.Serializable;

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
}
