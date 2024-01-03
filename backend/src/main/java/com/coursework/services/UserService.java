package com.coursework.services;

import com.coursework.models.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByEmailAndPassword(String email, String password);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}

