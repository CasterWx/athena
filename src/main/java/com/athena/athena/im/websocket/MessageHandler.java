package com.athena.athena.im.websocket;

import com.athena.athena.im.bean.ChatMessage;
import com.athena.athena.im.bean.UserData;
import com.athena.athena.im.dao.ChatMessageDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Map<Long, WebSocketSession> SESSION = new HashMap<>();

    @Autowired
    public ChatMessageDAO messageDAO ;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Long uid = (Long) session.getAttributes().get("uid");

        JsonNode jsonNode = objectMapper.readTree(message.getPayload());

        Long toId = jsonNode.get("toId").asLong();
        String msg = jsonNode.get("said").asText();
        Boolean type = jsonNode.get("type").asBoolean();
        Integer saidId = jsonNode.get("saidId").asInt();
        ObjectId replyId = null;
        // type true message 发起
        // type false reply 回复
        if (!type) {
            replyId = ObjectId.get();
        }

        ChatMessage sendMsg = ChatMessage.builder()
                .fromUser(UserData.USER_MAP.get(uid))
                .toUser(UserData.USER_MAP.get(toId))
                .said(msg)
                .type(type)
                .saidId(saidId)
                .replyId(replyId)
                .sendDate(new Date())
                .build();

        messageDAO.saveMessage(sendMsg);

        WebSocketSession socketSession = SESSION.get(toId);
        // 当前在线
        if (socketSession!=null && socketSession.isOpen()){
            socketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(sendMsg)));
            messageDAO.updateMessageState(sendMsg.getId(), 2);
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        Long uid = (Long) session.getAttributes().get("uid");
        SESSION.put(uid, session);

        // 通知其他人自己上线了
        ConMsg conMsg = new ConMsg(1, UserData.USER_MAP.get(uid).getUsername() + "进入聊天。");
        SESSION.forEach((s,y) -> {
            if (s!=uid){
                try {
                    y.sendMessage(new TextMessage(objectMapper.writeValueAsString(conMsg)));
                    // 除了通知他人，还要告诉自己现在谁在线
                    ConMsg userChat = new ConMsg(0, UserData.USER_MAP.get(s).getUsername() + "当前在线。");
                    session.sendMessage(new TextMessage(objectMapper.writeValueAsString(userChat)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // 新上线的用户去拿自己的未读消息
        List<ChatMessage> messages = this.messageDAO.findListByFromAndToNotRead(uid, 1);
        messages.stream().forEach(
                message -> {
                    try {
                        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
                        messageDAO.updateMessageState(message.getId(), 2);
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                }
        );
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
        Long uid = (Long) session.getAttributes().get("uid");
        ConMsg conMsg = new ConMsg(-1, UserData.USER_MAP.get(uid).getUsername() + "退出连接。");
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(conMsg)));
        SESSION.remove(uid);
        logger.info("Exit Antz-IM！");
    }

    class ConMsg{
        private Integer status;
        private String msg;

        public ConMsg(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public Integer getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }
}
