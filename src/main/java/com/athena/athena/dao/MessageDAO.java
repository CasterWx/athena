package com.athena.athena.dao;

import com.athena.athena.bean.Reply;
import com.athena.athena.mongobean.Message;
import com.mongodb.client.result.DeleteResult;

public interface MessageDAO {

    Message findMessageById(Integer saidId);

    Message updateReply(Integer saidId, Reply reply);

    Message saveMessage(Message message);

    DeleteResult deleteMessage(Integer saidId);

}
