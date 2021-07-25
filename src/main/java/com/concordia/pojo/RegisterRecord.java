package com.concordia.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "register_record")
public class RegisterRecord implements Serializable {

    private static final long serialVersionUID = 8243079172090464012L;

    @Id
    @Column(name = "id", length = 48)
    private String id;

    @Column(name = "username", length = 16)
    private String username;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "captcha", length = 16)
    private String captcha;

    @Column(name = "send_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
