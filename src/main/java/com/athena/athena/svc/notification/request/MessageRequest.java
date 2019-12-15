package com.athena.athena.svc.notification.request;

import com.athena.athena.bean.Reply;

public class MessageRequest {
    private Integer saidId;
    private String said;
    private Reply reply;

    public Integer getSaidId() {
        return saidId;
    }

    public void setSaidId(Integer saidId) {
        this.saidId = saidId;
    }

    public String getSaid() {
        return said;
    }

    public void setSaid(String said) {
        this.said = said;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
