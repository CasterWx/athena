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
}
