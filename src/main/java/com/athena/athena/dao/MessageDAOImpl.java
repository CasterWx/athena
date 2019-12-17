package com.athena.athena.dao;

import com.athena.athena.bean.Reply;
import com.athena.athena.mongobean.Message;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Message findMessageById(Integer id) {
        return this.mongoTemplate.findById(id, Message.class);
    }

    @Override
    public Message updateReply(Integer saidId, Reply reply) {
        Message message = findMessageById(saidId);
        List<Reply> list = message.getReplys();
        if (list==null){
            list = new ArrayList<>();
        }
        list.add(reply);
        message.setReplys(list);
        return saveMessage(message);
    }

    @Override
    public Message saveMessage(Message message) {
        return this.mongoTemplate.save(message);
    }

    @Override
    public DeleteResult deleteMessage(Integer id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query, Message.class);
    }
}
