package com.coursework.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Column(name = "status_id")
    private int status;

    public User() {
    }

    public User(String userName, String password, String mail, String phone, int status) {
        this.userName = userName;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.mail = mail;
        this.phone = phone;
        this.status = status;
        setPassword(password);
    }
}