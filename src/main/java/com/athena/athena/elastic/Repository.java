package com.athena.athena.elastic;

import com.athena.athena.bean.Message;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Repository extends ElasticsearchRepository<Message,Long>{
    Message findBySaidId(Integer saidId);
//    List<Message> findByReplyDataLike(String replyData);
//    List<Message> findByReplyId(Integer replyId);
    List<Message> findBySaidLike(String said);
}
