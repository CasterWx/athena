package com.athena.athena.svc.notification;

import com.athena.athena.bean.Message;
import com.athena.athena.bean.Reply;
import com.athena.athena.common.HResult;
import com.athena.athena.common.Result;
import com.athena.athena.elastic.Repository;
import com.athena.athena.manager.SearchFactory;
import com.athena.athena.manager.SearchFieldregister;
import com.athena.athena.svc.notification.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class NotificationController  {

    @Autowired
    private Repository repository;

    /**
     * @param request
     * @return
     * 插入一条reply,如果request中saidId为NULL则自动寻找一条相似的said插入。
     * 如果没有相似的就创建新的said。
     * */
    @RequestMapping(value = "/insert")
    private Result<Message> insert(@RequestBody MessageRequest request){
        Result<Message> result = new Result<>(HResult.H_OK);
        Integer saidId = request.getSaidId();
        String said = request.getSaid();
        Message message = null;
        if (saidId != null){
            message = repository.findBySaidId(saidId);
            if (message!=null){
                List<Reply> replies =  message.getReplys();
                replies.add(request.getReply());
                message.setReplys(replies);
                repository.save(message);
                result.setData(message);
                return result;
            } else {

            }
        } else if (said != null){
            // 目前只根据saidId插入
            List<Message> list = repository.findBySaidLike(said);
            Message msg = list.get(0);
            List<Reply> replies = msg.getReplys();
            replies.add(request.getReply());
            msg.setReplys(replies);
            repository.save(msg);
            result.setData(msg);
            return result;
        }
        return result;
    }

    /**
     * @param filedName 查询字段名
     * @param value 查询值
     * */
    @GetMapping(value = "/search")
    public Result<List<Message>> get(@RequestParam(value = "filed") String filedName,
                       @RequestParam(value = "value") String value) {
        Result<List<Message>> result = new Result<>(HResult.H_OK);
        SearchFieldregister searchFieldregister = SearchFactory.getSearchFactory(filedName);

        List<Message> messages = searchFieldregister.search(value);
        if (CollectionUtils.isEmpty(messages)){
            result.setData(messages);
        } else {
            result.setStatus(HResult.H_NO_DATA);
        }
        return result;
    }

    @GetMapping(value = "/getall")
    public List<Message> getAll() {
        List<Message> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @RequestMapping(value = "/save")
    public Message save() {
        List<Reply> list = new ArrayList<>();
        list.add(new Reply(1001,-10,"在忙",20001,false,0));
        list.add(new Reply(1002,0,"干什么",-1,false,0));
        list.add(new Reply(1003,10,"在鸭~",-1,false,0));
        list.add(new Reply(1004,20,"https://pic.cnblogs.com/avatar/1291955/20191008170543.png",-1,true,0));
        Message result = new Message(1000,"antzuhl","athena","在吗",list,0);
        repository.save(result);
        return result;
    }
}
