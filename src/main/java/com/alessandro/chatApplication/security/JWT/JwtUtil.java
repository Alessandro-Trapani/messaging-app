package com.alessandro.chatApplication.security.JWT;

import com.alessandro.chatApplication.exception.TokenNotValidException;
import com.alessandro.chatApplication.model.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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


    public String extractEmail(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.get("email", String.class);
    }


    public void isValidToken(String token) {

        if (token == null || token.isEmpty()) {
           throw new TokenNotValidException("token is null/empty");
        }


        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

        } catch (io.jsonwebtoken.security.SecurityException | io.jsonwebtoken.MalformedJwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.err.println("JWT token expired: " + e.getMessage());
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            System.err.println("Unsupported JWT token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT token is null or empty: " + e.getMessage());
        }

    }
    }



