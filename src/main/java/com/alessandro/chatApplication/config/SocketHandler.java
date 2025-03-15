package com.alessandro.chatApplication.config;

import com.alessandro.chatApplication.controller.SessionController;
import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.ChatMessage;
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
    private final SessionController sessionController;

    public SocketHandler(SessionRegistry sessionRegistry, SessionController sessionController) {
        this.sessionRegistry = sessionRegistry;
        this.sessionController = sessionController;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userEmail = (String) session.getAttributes().get("userEmail");
        System.out.println(session.toString());
        sessionRegistry.register(userEmail, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession senderSession,
                                     TextMessage message) throws IOException {

        String senderEmail = (String) senderSession.getAttributes().get("userEmail");

        String userMessage = message.getPayload();
        System.out.println(userMessage);


        String recipientEmail = Objects.requireNonNull(senderSession.getUri()).toString().split("/")[senderSession.getUri().toString().split("/").length - 1];
        sessionController.saveMessageWithEmail(senderEmail, recipientEmail,message.getPayload());


        System.out.println("Extracted recipientEmail: " + recipientEmail);

        sessionRegistry.getSession(recipientEmail).ifPresent(recipientSession -> {
            try {
                String formattedMessage = String.format("[From %s] %s",
                        senderEmail, userMessage);

                AppUser sender = sessionController.findByEmail(senderEmail);
                AppUser recipient = sessionController.findByEmail(recipientEmail);

                List<ChatMessage> messages = sessionController.findMessagesFromUser(sender,recipient);
                for(ChatMessage chatMessage : messages){
                    recipientSession.sendMessage(new TextMessage(chatMessage.getContent()));
                }
                recipientSession.sendMessage(new TextMessage(formattedMessage));
            } catch (Exception e) {

            }
        });
    }
}