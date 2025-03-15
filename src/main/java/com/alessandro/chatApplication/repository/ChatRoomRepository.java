package com.alessandro.chatApplication.repository;

import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findByRecipientAndSender(AppUser recipient, AppUser sender);
}
