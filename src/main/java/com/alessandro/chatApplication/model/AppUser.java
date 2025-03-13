package com.alessandro.chatApplication.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser {

    @Id
    @SequenceGenerator(
            name = "AppUser_sequence",
            sequenceName = "AppUser_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "AppUser_sequence"
    )
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private Map<AppUser, ChatRoom> chatRoom;

    @OneToMany(mappedBy = "sender")
    private List<ChatRoom> sentChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "recipient")
    private List<ChatRoom> receivedChatRooms = new ArrayList<>();
    public static String ONLINE = "Online";
    public static String OFFLINE = "Offline";


    public AppUser(List<ChatRoom> receivedChatRooms, List<ChatRoom> sentChatRooms, Map<AppUser, ChatRoom> chatRoom, String password, String email, String lastName, String firstName) {
        this.receivedChatRooms = receivedChatRooms;
        this.sentChatRooms = sentChatRooms;
        this.chatRoom = chatRoom;
        this.status = Status.OFFLINE;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}