package com.alessandro.chatApplication.service;

import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.Status;
import com.alessandro.chatApplication.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        appUserRepository.save(new AppUser(null,null,null,bCryptPasswordEncoder.encode("asdf"),"alessandro.trapani03@gmail.com","ale","trapani"));
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        createSampleUsers();
    }


    public AppUser findByEmail(){

        Optional<AppUser> user =  appUserRepository.findByEmail("alessandro.trapani03@gmail.com");
        System.out.println(user.isPresent());
        return user.orElse(null);
    }

    @Transactional
    public void createSampleUsers() {
        // Clear existing data (optional)
        appUserRepository.deleteAll();

        // Create and save sample users
        AppUser user1 = new AppUser();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password123");
        user1.setStatus(Status.ONLINE);

        AppUser user2 = new AppUser();
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setPassword("password456");
        user2.setStatus(Status.ONLINE);

        appUserRepository.save(user1);
        appUserRepository.save(user2);

        System.out.println("Sample users created successfully!");
    }
}
