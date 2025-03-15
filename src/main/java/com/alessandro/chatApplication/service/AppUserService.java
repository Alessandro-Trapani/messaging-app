package com.alessandro.chatApplication.service;
import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.repository.AppUserRepository;
import com.alessandro.chatApplication.security.JWT.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;


    }


    public Optional<AppUser> findByEmail(String email){
        return appUserRepository.findByEmail(email);
    }

    public void save(AppUser appUser){
        appUser.setPassword(encryptPassword(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    public String encryptPassword(String password){
        return  bCryptPasswordEncoder.encode(password);
    }

    public boolean validatePassword(String rawPassword, String hashedPassword) {

        return bCryptPasswordEncoder.matches(rawPassword, hashedPassword);
    }

}
