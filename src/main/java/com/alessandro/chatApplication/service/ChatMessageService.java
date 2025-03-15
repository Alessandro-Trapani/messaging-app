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
import java.util.List;

@Service
public class ChatMessageService {


    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService( ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void save(ChatMessage message){
        chatMessageRepository.save(message);
    }

    public List<ChatMessage> findMessagesFromUser(AppUser recipient, AppUser sender) {
        return chatMessageRepository.findByRecipientAndSender(recipient,sender);
    }
}