package com.concordia.common.exception;

import com.concordia.rpcDomain.common.ResultCode;

public class CustomException extends RuntimeException{

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

}
