package com.concordia.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_tag")
public class UserTag implements Serializable {


    private static final long serialVersionUID = -5760292334141596973L;

    @Id
    @Column(name = "id", nullable = false, length = 48)
    private String id;

    @Column(name = "user_id", nullable = false, length = 48)
    private String userId;

    @Column(name = "tag_name", length = 128)
    private String tagName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
