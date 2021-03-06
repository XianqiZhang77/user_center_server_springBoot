package com.concordia.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "article_tag")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = -634345417387924787L;

    @Id
    @Column(name = "id", length = 48)
    private String id;

    @Column(name = "article_id", nullable = false, length = 48)
    private String articleId;

    @Column(name = "tag_name", length = 128)
    private String tagName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
