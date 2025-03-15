package com.alessandro.chatApplication.security;

import com.alessandro.chatApplication.exception.TokenNotValidException;
import com.alessandro.chatApplication.security.JWT.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationManager {

    private final JwtUtil jwtUtil;

    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public void authenticate(String jwt){
       boolean isValid = jwtUtil.isValidToken(jwt);

       if(!isValid){
           throw new TokenNotValidException("JWT token is not valid");
       }
    }
}
