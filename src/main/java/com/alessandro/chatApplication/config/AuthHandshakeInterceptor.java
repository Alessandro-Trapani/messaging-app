package com.alessandro.chatApplication.config;

import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.security.AuthenticationManager;
import com.alessandro.chatApplication.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    private final AuthenticationManager authManager;
    private final AppUserService appUserService;

    public AuthHandshakeInterceptor(AuthenticationManager authManager, AppUserService appUserService) {
        this.authManager = authManager;
        this.appUserService = appUserService;

    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {

        // Extract user email from URL path
        String path = request.getURI().getPath();
        String targetEmail = path.substring(path.lastIndexOf('/') + 1);

        String token = request.getHeaders().getFirst("Authentication");
        authManager.authenticate(token);
        String authenticatedEmail = authManager.getEmail(token);

        if(targetEmail.equals(authenticatedEmail)){
            return  false;
        } else if (appUserService.findByEmail(targetEmail).isEmpty()) {
            return  false;
        }

        attributes.put("userEmail", authenticatedEmail);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }


}