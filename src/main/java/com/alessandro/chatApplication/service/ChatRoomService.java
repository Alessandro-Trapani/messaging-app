package com.alessandro.chatApplication.service;
import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.ChatRoom;
import com.alessandro.chatApplication.repository.AppUserRepository;
import com.alessandro.chatApplication.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final AppUserRepository appUserRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, AppUserRepository appUserRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.appUserRepository = appUserRepository;
        createSampleChatRoom();
    }

    @Transactional
    public void createSampleChatRoom() {
        // Clear existing data (optional)
        chatRoomRepository.deleteAll();

        // Fetch sample users
        AppUser user1 = appUserRepository.findByEmail("john.doe@example.com")
                .orElseThrow(() -> new RuntimeException("User not found"));
        AppUser user2 = appUserRepository.findByEmail("jane.smith@example.com")
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create and save a chat room
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setSender(user1);
        chatRoom.setRecipient(user2);

        chatRoomRepository.save(chatRoom);

        System.out.println("Sample chat room created successfully!");
    }
}