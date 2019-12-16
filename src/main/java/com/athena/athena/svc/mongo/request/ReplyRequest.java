package com.athena.athena.svc.mongo.request;

import com.athena.athena.bean.Reply;

public class ReplyRequest {
    private Integer saidId;
    private Integer replyMoon;
    private String replyData;
    private Integer customData;
    private boolean autoSaid;
    private Integer numberOf;

    public Reply toData(){
        Reply reply = new Reply();
        reply.setAutoSaid(this.autoSaid);
        reply.setCustomData(this.getCustomData());
        reply.setNumberOf(0);
        reply.setReplyMoon(this.getReplyMoon());
        reply.setReplyData(this.getReplyData());
        return reply;
    }

    public ReplyRequest(){}

    public ReplyRequest(Integer replyId, Integer replyMoon, String replyData, Integer customData, boolean autoSaid, Integer numberOf) {
        this.saidId = replyId;
        this.replyMoon = replyMoon;
        this.replyData = replyData;
        this.customData = customData;
        this.autoSaid = autoSaid;
        this.numberOf = numberOf;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + saidId +
                ", replyMoon=" + replyMoon +
                ", replyData='" + replyData + '\'' +
                ", customData=" + customData +
                ", autoSaid=" + autoSaid +
                ", numberOf=" + numberOf +
                '}';
    }

    public Integer getSaidId() {
        return saidId;
    }

    public void setSaidId(Integer replyId) {
        this.saidId = replyId;
    }

    public Integer getReplyMoon() {
        return replyMoon;
    }

    public void setReplyMoon(Integer replyMoon) {
        this.replyMoon = replyMoon;
    }

    public String getReplyData() {
        return replyData;
    }

    public void setReplyData(String replyData) {
        this.replyData = replyData;
    }

    public Integer getCustomData() {
        return customData;
    }

    public void setCustomData(Integer customData) {
        this.customData = customData;
    }

    public boolean isAutoSaid() {
        return autoSaid;
    }

    public void setAutoSaid(boolean autoSaid) {
        this.autoSaid = autoSaid;
    }

    public Integer getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(Integer numberOf) {
        this.numberOf = numberOf;
    }
}
