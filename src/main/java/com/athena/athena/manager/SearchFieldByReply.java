package com.athena.athena.manager;

import com.athena.athena.bean.Message;
import com.athena.athena.elastic.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SearchFieldByReply implements SearchFieldregister {

    @Autowired
    Repository repository;

    @Override
    public List<Message> search(String value) {
//        return repository.findByReplyDataLike(value);
        return new ArrayList<>();
    }
}
