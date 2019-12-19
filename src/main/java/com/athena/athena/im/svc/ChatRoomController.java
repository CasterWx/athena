package com.athena.athena.im.svc;


import com.athena.athena.im.bean.ChatMessage;
import com.athena.athena.im.dao.impl.ChatMessageDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ChatRoomController {

    private static final Logger log = LoggerFactory.getLogger(ChatRoomController.class);

    @Autowired
    ChatMessageDAOImpl chatMessageDAO ;

    @GetMapping("/chat/{myid}/{toid}")
    public ModelAndView chatRoom(@PathVariable Integer myid, @PathVariable Integer toid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chat");
        List<ChatMessage> chatMessages = chatMessageDAO.findListByFromAndTo(Long.valueOf(myid), Long.valueOf(toid));
        modelAndView.addObject("chatData",chatMessages);
        log.info("/chat controler");
        return modelAndView;
    }

}
