package com.coursework.models;

import com.coursework.enums.InstitutionType;
import com.coursework.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
