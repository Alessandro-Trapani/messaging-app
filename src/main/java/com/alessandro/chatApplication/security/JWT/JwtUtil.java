package com.alessandro.chatApplication.security.JWT;

import com.alessandro.chatApplication.model.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private static final String BEARER_PREFIX = "Bearer ";
    static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    private static final long EXPIRATION_TIME = 1000 * 60 * 60;


    public static String generateToken(AppUser user) {

        Map<String, Object> userClaims = new HashMap<>();
        userClaims.put("email", user.getEmail());  // Add email
        userClaims.put("firstName", user.getFirstName());  // Add first name
        userClaims.put("lastName", user.getLastName());  // Add last name


        return Jwts.builder()
                .addClaims(userClaims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Sign with secret key
                .compact();
    }
    public String extractTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return authorizationHeader.substring(BEARER_PREFIX.length());
        }

        return null;
    }

    public boolean isValidToken(String token) {
        try {

            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {

            return false;
        }
    }


}