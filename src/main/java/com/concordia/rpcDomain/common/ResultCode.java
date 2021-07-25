package com.concordia.rpcDomain.common;

public enum ResultCode {

    SUCCESS(0, "success"),
    REGISTER_CAPTCHA_SENT(2001, "email captcha has been sent"),
    FAIL(-1, "fail"),
    REGISTER_DATA_IS_WRONG(-2001, "registration information error"),
    MAIL_SEND_FAIL(-2002, "failed to send email"),
    WRONG_CAPTCHA(-2003, "wrong captcha"),
    REGISTER_RECORD_IS_EMPTY(-2004, "register record not found"),
    PERMISSION_SIGNATURE_ERROR(-2005, "permission signature failed"),
    PERMISSION_TOKEN_EXPIRED(-2006, "token has expired"),
    PERMISSION_TOKEN_INVALID(-2007, "token is invalid"),
    PERMISSION_DENIED(-2012, "no permission to do this operation"),
    USER_UN_VERIFIED(-2008, "account does not exist or unverified"),
    WRONG_PASSWORD(-2009, "password does not match"),
    USER_UN_LOGGED_IN(-2010, "user is not in login status"),
    SERVER_ERROR(-2011, "server error"),

    /* 参数错误: 10001-19999 */
    PARAM_IS_BLANK(10002, "param is empty"),


    /* */
    USER_NOT_EXIST(20001, "user does not exist"),
    RESULT_DATA_NONE(20002, "result cannot be found"),
    ;

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
