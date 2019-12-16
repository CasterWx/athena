package com.athena.athena.svc.mongo.request;

import com.athena.athena.bean.Reply;
import com.athena.athena.mongobean.Message;

import java.util.List;

public class MessageRequest {
    private Integer saidId;
    private String fromUser;
    private String toUser;
    private String said;
    private List<Reply> replys;
    private Integer numberOf;

    public Message toData(){
        Message message = new Message();
        message.setSaidId(this.saidId);
        message.setSaid(this.getSaid());
        message.setFromUser(this.getFromUser());
        message.setToUser(this.getToUser());
        message.setNumberOf(0);
        message.setReplys(this.getReplys());
        return message;
    }

    public Integer getSaidId() {
        return saidId;
    }

    public void setSaidId(Integer saidId) {
        this.saidId = saidId;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getSaid() {
        return said;
    }

    public void setSaid(String said) {
        this.said = said;
    }

    public List<Reply> getReplys() {
        return replys;
    }

    public void setReplys(List<Reply> replys) {
        this.replys = replys;
    }

    public Integer getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(Integer numberOf) {
        this.numberOf = numberOf;
    }
}
