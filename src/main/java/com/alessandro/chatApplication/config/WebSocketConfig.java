package com.alessandro.chatApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final SocketHandler socketHandler;
    private final AuthHandshakeInterceptor authInterceptor;

    public WebSocketConfig(SocketHandler socketHandler, AuthHandshakeInterceptor authInterceptor) {
        this.socketHandler = socketHandler;
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/user/{userEmail}")
                .addInterceptors(authInterceptor)
                .setAllowedOrigins("*");
    }
}