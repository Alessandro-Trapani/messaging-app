package com.alessandro.chatApplication.service;
import com.alessandro.chatApplication.model.ChatRoom;
import com.alessandro.chatApplication.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;


    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;


    }
    public void save(ChatRoom chatRoom){
        chatRoomRepository.save(chatRoom);
    }

}