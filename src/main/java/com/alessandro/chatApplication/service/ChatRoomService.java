package com.alessandro.chatApplication.service;
import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.ChatRoom;
import com.alessandro.chatApplication.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;


    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;


    }
    public void save(ChatRoom chatRoom){
        chatRoomRepository.save(chatRoom);
    }
    public Optional<ChatRoom> findByRecipientAndSender(AppUser recipient, AppUser sender) {

        Optional<ChatRoom> chatRoom = chatRoomRepository.findByRecipientAndSender(recipient, sender);

        if (chatRoom.isEmpty()) {
            chatRoom = chatRoomRepository.findByRecipientAndSender(sender, recipient);
        }

        return chatRoom;
    }


}