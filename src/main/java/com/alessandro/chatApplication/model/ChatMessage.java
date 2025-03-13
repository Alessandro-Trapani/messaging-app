package com.alessandro.chatApplication.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
@NoArgsConstructor
@Setter
@Getter
@Table(name = "chat_message")
@Entity
public class ChatMessage {
    @Id
    @SequenceGenerator(
            name = "ChatMessage_sequence",
            sequenceName = "ChatMessage_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ChatMessage_sequence"
    )
    private Long messageId;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AppUser sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private AppUser recipient;
    private String content;
    private Time timeStamp;
    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public ChatMessage(AppUser sender, AppUser recipient, String content, Time timeStamp, ChatRoom chatRoom) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timeStamp = timeStamp;
        this.chatRoom = chatRoom;
    }
}
