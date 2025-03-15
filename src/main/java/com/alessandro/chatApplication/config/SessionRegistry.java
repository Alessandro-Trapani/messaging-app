package com.alessandro.chatApplication.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SessionRegistry {
    private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void register(String email, WebSocketSession session) {
        sessions.put(email, session);
    }

    public Optional<WebSocketSession> getSession(String email) {
        return Optional.ofNullable(sessions.get(email));
    }

    public void remove(String email) {
        sessions.remove(email);
    }



}