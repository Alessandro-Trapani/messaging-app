package com.alessandro.chatApplication.controller;
import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.ChatMessage;
import com.alessandro.chatApplication.model.ChatRoom;
import com.alessandro.chatApplication.service.AppUserService;
import com.alessandro.chatApplication.service.ChatMessageService;
import com.alessandro.chatApplication.service.ChatRoomService;
import org.springframework.stereotype.Controller;


import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Controller
public class SessionController {

    private final ChatRoomService chatRoomService;
    private final AppUserService appUserService;
    private final ChatMessageService chatMessageService;

    public SessionController(ChatRoomService chatRoomService, AppUserService appUserService, ChatMessageService chatMessageService) {
        this.chatRoomService = chatRoomService;
        this.appUserService = appUserService;
        this.chatMessageService = chatMessageService;


    }

    public AppUser findByEmail(String email)
    {
       return appUserService.findByEmail(email).orElseThrow();
    }
    public void createChatRoomWithEmails(String email1, String email2){
        AppUser user1 = appUserService.findByEmail(email1).orElseThrow();
        AppUser user2 = appUserService.findByEmail(email2).orElseThrow();

        if(!chatRoomService.findByRecipientAndSender(user1,user2).isPresent()){
            chatRoomService.save(new ChatRoom(user1,user2));
        }


    }

    public void saveMessageWithEmail(String senderEmail, String recipientEmail, String message) {
        System.out.println("saving message");
        AppUser sender = appUserService.findByEmail(senderEmail).orElseThrow();
        AppUser recipient = appUserService.findByEmail(recipientEmail).orElseThrow();
        ChatRoom chatRoom = chatRoomService.findByRecipientAndSender(recipient,sender).orElseThrow();
        chatMessageService.save(new ChatMessage(sender,recipient,message, Time.valueOf(LocalTime.now()) ,chatRoom));
    }

    public List<ChatMessage> findMessagesFromUser(AppUser recipient, AppUser sender){
        return chatMessageService.findMessagesFromUser(recipient,sender);
    }
}
