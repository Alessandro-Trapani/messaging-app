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

@Controller
public class TestController {

    AppUserService appUserService;
    ChatMessageService chatMessageService;
    ChatRoomService chatRoomService;

    public TestController(AppUserService appUserService, ChatMessageService chatMessageService, ChatRoomService chatRoomService) {
        this.appUserService = appUserService;
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;
        AppUser appUser = new AppUser( "email@gmail.com","password","","");
        AppUser appUser1 = new AppUser( "emailSecond@gmail.com","password","","");
        ChatRoom chatRoom = new ChatRoom(appUser,appUser1);
        appUserService.save(appUser);
        appUserService.save(appUser1);
        chatRoomService.save(chatRoom);
        chatMessageService.save(new ChatMessage(appUser,appUser1,"hello", Time.valueOf(LocalTime.now()) ,chatRoom));


    }


}
