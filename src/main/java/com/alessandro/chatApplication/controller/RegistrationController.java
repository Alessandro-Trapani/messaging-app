package com.alessandro.chatApplication.controller;

import com.alessandro.chatApplication.DTO.RegistrationDTO;
import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class RegistrationController {

    private final AppUserService appUserService;


    public RegistrationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationDTO registrationDTO) {

        if (registrationDTO.email() == null || !registrationDTO.email().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return ResponseEntity.badRequest().body("Invalid email format.");
        }
        if (registrationDTO.password() == null || registrationDTO.password().length() < 6) {
            return ResponseEntity.badRequest().body("Password must be at least 6 characters.");
        }
        if (registrationDTO.firstName() == null || registrationDTO.firstName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("First name is required.");
        }
        if (registrationDTO.lastName() == null || registrationDTO.lastName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Last name is required.");
        }
        if (appUserService.findByEmail(registrationDTO.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        registerUser(registrationDTO.email(), registrationDTO.password(), registrationDTO.lastName(), registrationDTO.firstName());

        return ResponseEntity.ok("User registered successfully.");
    }

    public void registerUser(String email,String password, String lastName, String firstName){
        AppUser appUser = new AppUser(email,password,lastName,firstName);
        appUserService.save(appUser);
    }
}
