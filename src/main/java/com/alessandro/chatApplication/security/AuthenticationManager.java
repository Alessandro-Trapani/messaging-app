package com.alessandro.chatApplication.security;
import com.alessandro.chatApplication.security.JWT.JwtUtil;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationManager {

    private final JwtUtil jwtUtil;

    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public void authenticate(String jwt){
       jwtUtil.isValidToken(jwt);
    }

    public String getEmail(String jwt){
        return jwtUtil.extractEmail(jwt);
    }


}
