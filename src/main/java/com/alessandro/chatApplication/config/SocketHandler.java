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

    private final SessionRegistry sessionRegistry;

    public SocketHandler(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userEmail = (String) session.getAttributes().get("userEmail");
        sessionRegistry.register(userEmail, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession senderSession,
                                     TextMessage message) throws IOException {

        String senderEmail = (String) senderSession.getAttributes().get("userEmail");

        // Expected message format: "recipient@example.com:Hello there!"
        String userMessage = message.getPayload();
        System.out.println(userMessage);


        String email = Objects.requireNonNull(senderSession.getUri()).toString().split("/")[senderSession.getUri().toString().split("/").length - 1];



        System.out.println("Extracted email: " + email);

        sessionRegistry.getSession(email).ifPresent(recipientSession -> {
            try {
                String formattedMessage = String.format("[From %s] %s",
                        senderEmail, userMessage);
                recipientSession.sendMessage(new TextMessage(formattedMessage));
            } catch (IOException e) {
                // Handle send failure
            }
        });
    }
}