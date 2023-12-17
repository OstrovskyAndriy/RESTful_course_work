package com.coursework.models;

import com.coursework.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String  mail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private UserStatus status;
}
