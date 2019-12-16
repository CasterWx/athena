package com.athena.athena.dao;

import com.athena.athena.mongobean.Message;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.List;

public interface MessageDAO {

    List<Message> findListByFromAndTo(Long fromId, Long toId, Integer page, Integer rows);

    List<Message> findListByFromAndToNotRead(Long toId, Integer status);

    Message findMessageById(String id);

    UpdateResult updateMessageState(ObjectId id, Integer status);

    Message saveMessage(Message message);

    DeleteResult deleteMessage(String id);

}
