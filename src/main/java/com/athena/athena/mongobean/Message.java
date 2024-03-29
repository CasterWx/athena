package com.athena.athena.mongobean;

import com.athena.athena.bean.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "athena_chats")
public class Message {
    @Id
    @Indexed
    @Field("saidId")
    private Integer saidId;
    @Field("fromUser")
    private String fromUser;
    @Field("toUser")
    private String toUser;
    @Field("said")
    private String said;
    @Field("replys")
    private List<Reply> replys;
    @Field("numberOf")
    private Integer numberOf;

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
