package com.alessandro.chatApplication.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    public static String ONLINE = "Online";
    public static String OFFLINE = "Offline";

    public AppUser(  String password, String email, String lastName, String firstName) {
        this.status = Status.OFFLINE;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}