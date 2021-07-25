package com.concordia.rpcDomain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserPreferenceResp implements Serializable {


    private static final long serialVersionUID = 3455327315221927300L;

    @JsonProperty("todo_notice")
    private String todoNotice;

    @JsonProperty("system_message_notice")
    private String sysMessageNotice;

    @JsonProperty("other_user_message_notice")
    private String otherUserMessageNotice;

    public String getTodoNotice() {
        return todoNotice;
    }

    public void setTodoNotice(String todoNotice) {
        this.todoNotice = todoNotice;
    }

    public String getSysMessageNotice() {
        return sysMessageNotice;
    }

    public void setSysMessageNotice(String sysMessageNotice) {
        this.sysMessageNotice = sysMessageNotice;
    }

    public String getOtherUserMessageNotice() {
        return otherUserMessageNotice;
    }

    public void setOtherUserMessageNotice(String otherUserMessageNotice) {
        this.otherUserMessageNotice = otherUserMessageNotice;
    }
}
