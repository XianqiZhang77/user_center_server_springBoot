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
    @Column(name = "id", nullable = false, length = 64)
    private String id;
}
