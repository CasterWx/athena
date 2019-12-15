package com.athena.athena.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "athena_chats", type = "docs", shards = 1, replicas = 0)
public class Message {
    @Id
    @Field(type = FieldType.Integer)
    private Integer saidId;
    private String fromUser;
    private String toUser;
    private String said;
    @Field(type = FieldType.Object)
    private List<Reply> replys;
    @Field(type = FieldType.Integer)
    private Integer numberOf;

    public Message(){}

    public Message(Integer saidId, String fromUser, String toUser, String said, List<Reply> replys, Integer numberOf) {
        this.saidId = saidId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.said = said;
        this.replys = replys;
        this.numberOf = numberOf;
    }

    @Override
    public String toString() {
        return "Message{" +
                "saidId=" + saidId +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", said='" + said + '\'' +
                ", replys=" + replys +
                ", numberOf=" + numberOf +
                '}';
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
