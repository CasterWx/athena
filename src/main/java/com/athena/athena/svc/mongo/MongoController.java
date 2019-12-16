package com.athena.athena.svc.mongo;

import com.athena.athena.bean.Reply;
import com.athena.athena.common.HResult;
import com.athena.athena.common.Result;
import com.athena.athena.dao.MessageDAOImpl;
import com.athena.athena.mongobean.Message;
import com.athena.athena.svc.mongo.request.MessageRequest;
import com.athena.athena.svc.mongo.request.ReplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mongo")
public class MongoController {

    @Autowired
    private MessageDAOImpl messageDAO ;


    @RequestMapping(value = "/reply")
    private Result<Message> insertReply(@RequestBody ReplyRequest request){
        Result<Message> result = new Result<>(HResult.H_OK);
        Reply reply = request.toData();
        Message message = messageDAO.findMessageById(request.getSaidId());
        if (message.getReplys()!=null){
            Reply max = message.getReplys().stream().max((e1, e2)-> e1.getReplyId().compareTo(e2.getReplyId())).get();
            reply.setReplyId(max.getReplyId()+1);
        } else {
            reply.setReplyId(request.getSaidId()+1);
        }
        message = messageDAO.updateReply(request.getSaidId(),reply);
        result.setData(message);
        return result;
    }

    @RequestMapping(value = "/message")
    private Result<Message> insertMessage(@RequestBody MessageRequest request){
        Result<Message> result = new Result<>(HResult.H_OK);

        Message message = messageDAO.findMessageById(request.getSaidId());
        if (message!=null){
            result.setStatus(HResult.H_SAID_HAS);
            result.setMessage("SaidId重复");
            return result;
        }

        message = messageDAO.saveMessage(request.toData());

        result.setData(message);
        return result;
    }

}
