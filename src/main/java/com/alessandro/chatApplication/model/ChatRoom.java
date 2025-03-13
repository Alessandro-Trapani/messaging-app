package com.alessandro.chatApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "chat_room")
@Entity
public class ChatRoom {
    @Id
    @SequenceGenerator(
            name = "ChatRoom_sequence",
            sequenceName = "ChatRoom_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ChatRoom_sequence"
    )
    private Long chatId;

    @ManyToOne
    @JoinColumn(name = "sender_id") // Maps to AppUser's sentChatRooms
    private AppUser sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id") // Maps to AppUser's receivedChatRooms
    private AppUser recipient;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> messages = new ArrayList<>();

    public ChatRoom(AppUser sender, AppUser recipient, List<ChatMessage> messages) {
        this.sender = sender;
        this.recipient = recipient;
        this.messages = messages;
    }
}
