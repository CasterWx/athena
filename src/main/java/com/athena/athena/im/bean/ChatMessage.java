package com.athena.athena.im.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "chat_message")
public class ChatMessage {
    @Id
    private ObjectId id;
    @Indexed
    @Field("saidId")
    private Integer saidId;
    @Indexed
    @Field("replyId")
    private ObjectId replyId;
    @Field("type")
    private Boolean type;
    @Field("said")
    private String said;
    @Indexed
    private Integer status;
    @Field("send_date")
    @Indexed
    private Date sendDate;
    @Field("read_date")
    @Indexed
    private Date readDate;
    @Indexed
    private User fromUser;
    @Indexed
    private User toUser;
}
