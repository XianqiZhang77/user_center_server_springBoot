package com.concordia.rpcDomain.response;

import java.io.Serializable;
import java.util.List;

public class UserCenterVOResp implements Serializable {

    private static final long serialVersionUID = -3327817411007547088L;

    //VO view object
    //DTO: Data transfer Object
    //DAO: Data assess Object

    private String username;

    private String personalProfile;

    private String provinceAndCity;

    private List<String> userTagList;

    private List<ArticleResponse> articleList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getProvinceAndCity() {
        return provinceAndCity;
    }

    public void setProvinceAndCity(String provinceAndCity) {
        this.provinceAndCity = provinceAndCity;
    }

    public List<String> getUserTagList() {
        return userTagList;
    }

    public void setUserTagList(List<String> userTagList) {
        this.userTagList = userTagList;
    }

    public List<ArticleResponse> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleResponse> articleList) {
        this.articleList = articleList;
    }
}
