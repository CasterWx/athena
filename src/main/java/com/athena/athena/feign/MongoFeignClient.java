package com.athena.athena.feign;

import com.athena.athena.common.Result;
import com.athena.athena.mongobean.Message;
import com.athena.athena.svc.mongo.request.MessageRequest;
import com.athena.athena.svc.mongo.request.ReplyRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "mongo")
public interface MongoFeignClient {
    @RequestMapping(value = "/reply")
    Result<Message> insertReply(@RequestBody ReplyRequest request);

    @RequestMapping(value = "/message")
    Result<Message> insertMessage(@RequestBody MessageRequest request);
}
