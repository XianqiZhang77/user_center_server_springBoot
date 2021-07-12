package com.concordia.common.strategy;

public enum OperatorStrategyEnum {
    SUCCESS("SUCCESS", "success"),
    FAIL("FAIL", "fail"),
    UN_KNOW("UN_KNOWN", "unknown error"),
    ENAIL_FAIL("EMAIL_FAIL", "fail to send captcha");


    /**
     * operator code
     */
    private String code;

    /**
     * description
     */
    private String description;

    OperatorStrategyEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
