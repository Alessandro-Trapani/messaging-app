package com.alessandro.chatApplication.controller;

import com.alessandro.chatApplication.DTO.UserDto;
import com.alessandro.chatApplication.security.JWT.JwtUtil;
import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@Controller
@RestController
public class LoginController {

    private final AppUserService appUserService;


    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody UserDto userDto) {
        Optional<AppUser> user = appUserService.findByEmail(userDto.email());
        System.out.println(userDto.email());

        if (user.isPresent()) {

            if (appUserService.validatePassword(userDto.password(), user.get().getPassword())) {

                return ResponseEntity.ok("Authentication token : "+ JwtUtil.generateToken(user.get()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Incorrect password.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with email " + userDto.email() + " not found.");
        }
    }
}
