package com.security.bitbybitmultiplayergame.config;

import handler.SocketConnectionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements   WebSocketConfigurer{

    private final SocketConnectionHandler socketConnectionHandler;

    public WebSocketConfig(SocketConnectionHandler socketConnectionHandler) {
        this.socketConnectionHandler = socketConnectionHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketConnectionHandler, "/event").setAllowedOrigins("*");
    }


}

