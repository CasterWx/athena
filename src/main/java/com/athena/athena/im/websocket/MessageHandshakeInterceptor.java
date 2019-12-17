package com.athena.athena.im.websocket;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class MessageHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("Welcome to Athena!");
        String requestPath = serverHttpRequest.getURI().getPath();
        String []split = StringUtils.split(requestPath, '/');
        if (split.length!=2){
            return false;
        }
        if (!StringUtils.isNumeric(split[1])){
            return false;
        }
        map.put("uid", Long.valueOf(split[1]));
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("Athena Connect Success!");
    }
}
