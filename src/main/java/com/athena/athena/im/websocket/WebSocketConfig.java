package com.athena.athena.im.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    public MessageHandshakeInterceptor messageHandshakeInterceptor ;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getWebSocketHandler(), "/ws/{uid}").setAllowedOrigins("*").addInterceptors(this.messageHandshakeInterceptor);
    }

    @Bean
    public WebSocketHandler getWebSocketHandler(){
        return new MessageHandler();
    }
}
