package com.alessandro.chatApplication.service;
import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.ChatMessage;
import com.alessandro.chatApplication.model.ChatRoom;
import com.alessandro.chatApplication.repository.AppUserRepository;
import com.alessandro.chatApplication.repository.ChatMessageRepository;
import com.alessandro.chatApplication.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalTime;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final AppUserRepository appUserRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatRoomRepository chatRoomRepository, AppUserRepository appUserRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.appUserRepository = appUserRepository;
        createSampleMessages();
    }

    @Transactional
    public void createSampleMessages() {


        // Fetch sample users and chat room
        AppUser user1 = appUserRepository.findByEmail("john.doe@example.com")
                .orElseThrow(() -> new RuntimeException("User not found"));
        AppUser user2 = appUserRepository.findByEmail("jane.smith@example.com")
                .orElseThrow(() -> new RuntimeException("User not found"));
        ChatRoom chatRoom = chatRoomRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        // Create and save sample messages
        ChatMessage message1 = new ChatMessage();
        message1.setSender(user1);
        message1.setRecipient(user2);
        message1.setContent("Hi Jane! How are you?");
        message1.setTimeStamp(Time.valueOf(LocalTime.of(10, 0)));
        message1.setChatRoom(chatRoom);

        ChatMessage message2 = new ChatMessage();
        message2.setSender(user2);
        message2.setRecipient(user1);
        message2.setContent("Hi John! I am good, thanks!");
        message2.setTimeStamp(Time.valueOf(LocalTime.of(10, 5)));
        message2.setChatRoom(chatRoom);

        ChatMessage message3 = new ChatMessage();
        message3.setSender(user1);
        message3.setRecipient(user2);
        message3.setContent("Do you want to grab lunch?");
        message3.setTimeStamp(Time.valueOf(LocalTime.of(10, 10)));
        message3.setChatRoom(chatRoom);

        chatMessageRepository.save(message1);
        chatMessageRepository.save(message2);
        chatMessageRepository.save(message3);

        System.out.println("Sample messages created successfully!");
    }
}