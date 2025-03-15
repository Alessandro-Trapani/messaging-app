package com.alessandro.chatApplication.config;

import com.alessandro.chatApplication.security.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    private final AuthenticationManager authenticationManager;
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();


    public SocketHandler(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
          }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {
        System.out.println(message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String authHeader = Objects.requireNonNull(
                session.getHandshakeHeaders().get("Authentication")
        ).toString();
        // header has two parenthesis like: [header] by default, so i substring it
        if (authHeader.length() >= 2) {
            authHeader = authHeader.substring(1, authHeader.length() - 1);
        }

        authenticationManager.authenticate(authHeader);
        sessions.add(session);
    }
}