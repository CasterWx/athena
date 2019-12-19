package com.athena.athena.im.dao.impl;

import com.athena.athena.im.bean.ChatMessage;
import com.athena.athena.im.dao.ChatMessageDAO;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ChatMessageDAOImpl implements ChatMessageDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ChatMessage> findListByFromAndTo(Long fromId, Long toId) {
        Criteria fromList = new Criteria().andOperator(
                Criteria.where("fromUser.id").is(fromId),
                Criteria.where("toUser.id").is(toId)
        );
        Criteria toList = new Criteria().andOperator(
                Criteria.where("fromUser.id").is(toId),
                Criteria.where("toUser.id").is(fromId)
        );
        Criteria criteria = new Criteria().orOperator(fromList, toList);

        Query query = new Query(criteria);
        System.out.println(query);
        return this.mongoTemplate.find(query, ChatMessage.class);
    }

    @Override
    public List<ChatMessage> findListByFromAndToNotRead(Long toId, Integer status) {

        Criteria toList = new Criteria().andOperator(
                Criteria.where("toUser.id").is(toId),
                Criteria.where("status").is(status)
        );

        Query query = new Query(toList);
        System.out.println(query);
        return this.mongoTemplate.find(query, ChatMessage.class);
    }

    @Override
    public ChatMessage findMessageById(String id) {
        return this.mongoTemplate.findById(new ObjectId(id), ChatMessage.class);
    }

    @Override
    public UpdateResult updateMessageState(ObjectId id, Integer status) {
        Query query = Query.query(Criteria.where("id").is(id));

        Update update = Update.update("status", status);

        if (status.intValue() == 1){
            update.set("send_date", new Date());
        } else if (status.intValue() == 2){
            update.set("read_date", new Date());
        }
        return this.mongoTemplate.updateFirst(query, update, ChatMessage.class);
    }

    @Override
    public ChatMessage saveMessage(ChatMessage message) {
        message.setId(ObjectId.get());
        message.setSendDate(new Date());
        message.setStatus(1);
        return this.mongoTemplate.save(message);
    }

    @Override
    public DeleteResult deleteMessage(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query, ChatMessage.class);
    }
}
