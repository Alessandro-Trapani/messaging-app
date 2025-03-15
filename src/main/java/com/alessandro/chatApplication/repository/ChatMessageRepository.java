package com.alessandro.chatApplication.repository;

import com.alessandro.chatApplication.model.AppUser;
import com.alessandro.chatApplication.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRecipientAndSender(AppUser recipient, AppUser sender);
}
