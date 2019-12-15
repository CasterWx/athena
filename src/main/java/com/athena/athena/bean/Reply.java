package com.athena.athena.bean;

public class Reply {
    private Integer replyId;
    private Integer replyMoon;
    private String replyData;
    private Integer customData;
    private boolean autoSaid;
    private Integer numberOf;

    public Reply(){}

    public Reply(Integer replyId, Integer replyMoon, String replyData, Integer customData, boolean autoSaid, Integer numberOf) {
        this.replyId = replyId;
        this.replyMoon = replyMoon;
        this.replyData = replyData;
        this.customData = customData;
        this.autoSaid = autoSaid;
        this.numberOf = numberOf;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", replyMoon=" + replyMoon +
                ", replyData='" + replyData + '\'' +
                ", customData=" + customData +
                ", autoSaid=" + autoSaid +
                ", numberOf=" + numberOf +
                '}';
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
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
