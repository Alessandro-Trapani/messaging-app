package com.alessandro.chatApplication.controller;

import com.alessandro.chatApplication.service.AppUserService;
import com.alessandro.chatApplication.service.ChatMessageService;
import com.alessandro.chatApplication.service.ChatRoomService;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    AppUserService appUserService;
    ChatMessageService chatMessageService;
    ChatRoomService chatRoomService;

    public TestController(AppUserService appUserService, ChatMessageService chatMessageService, ChatRoomService chatRoomService) {
        this.appUserService = appUserService;
        this.chatMessageService = chatMessageService;
        this.chatRoomService = chatRoomService;

        // Correct order: create users FIRST
        appUserService.createSampleUsers(); // <-- Must come first
        chatRoomService.createSampleChatRoom(); // <-- Depends on users
        chatMessageService.createSampleMessages(); // <-- Depends on users and chat room
    }


}
