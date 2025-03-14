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

    public ChatMessageService( ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void save(ChatMessage message){
        chatMessageRepository.save(message);
    }

}