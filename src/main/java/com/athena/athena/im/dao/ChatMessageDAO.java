package com.athena.athena.im.dao;

import com.athena.athena.im.bean.ChatMessage;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.List;

public interface ChatMessageDAO {

    List<ChatMessage> findListByFromAndTo(Long fromId, Long toId);

    List<ChatMessage> findListByFromAndToNotRead(Long toId, Integer status);

    ChatMessage findMessageById(String id);

    UpdateResult updateMessageState(ObjectId id, Integer status);

    ChatMessage saveMessage(ChatMessage message);

    DeleteResult deleteMessage(String id);

}
