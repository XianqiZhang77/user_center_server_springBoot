package com.concordia.rpcDomain.common;

public enum ResultCode {

    SUCCESS(0, "success"),
    REGISTER_CAPICHA_SENT(2001, "email captcha has been sent"),
    FAIL(-1, "fail"),
    REGISTER_DATA_IS_WRONG(-2001, "registration information error"),
    MAIL_SEND_FAIL(-2002, "failed to send email"),
    WRONG_CAPTCHA(-2003, "wrong captcha"),
    REGISTER_RECORD_IS_EMPTY(-2004, "register record not found");

    int code;
    String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
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
}
