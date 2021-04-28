package com.laide.LaideLibrary.service.serviceImpl;

import com.laide.LaideLibrary.entities.User;
import com.laide.LaideLibrary.exception.ResourceNotFoundException;
import com.laide.LaideLibrary.repository.UserRepository;
import com.laide.LaideLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException("User with id " + id + " is not found");
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException("User with id " + id + " is not found");
        }
        user.setUserId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException("User with id " + id + " is not found");
        }
        userRepository.deleteById(id);
    }
}
