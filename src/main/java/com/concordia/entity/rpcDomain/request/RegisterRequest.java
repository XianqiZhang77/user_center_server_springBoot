package com.concordia.entity.rpcDomain.request;

import java.io.Serializable;

public class RegisterRequest implements Serializable {

    private static final long serialVersionUID = 3101231337961524366L;

    private String username;

    private String password;

    private String email;

    private String captcha;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
