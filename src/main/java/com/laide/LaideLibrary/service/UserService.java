package com.laide.LaideLibrary.service;

import com.laide.LaideLibrary.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(User user);
    User getUser(long id);
    List<User> getAllUsers();
    User updateUser(long id, User user);
    void deleteUser(long id);
}