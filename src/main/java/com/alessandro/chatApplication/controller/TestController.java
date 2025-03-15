package com.alessandro.chatApplication.controller;

import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.ChatRoom;
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
        AppUser appUser = new AppUser( "email@gmail.com","password","","");
        AppUser appUser1 = new AppUser( "emailSecond@gmail.com","password","","");
        AppUser appUser2 = new AppUser( "emailThird@gmail.com","password","","");
        appUserService.save(appUser);
        appUserService.save(appUser1);
        appUserService.save(appUser2);
        chatRoomService.save( new ChatRoom(appUser, appUser1));
        System.out.println(chatRoomService.findByRecipientAndSender(appUser,appUser2).isPresent()+ "-----------------------------------------------------------------------------------------------------");

    }


}
