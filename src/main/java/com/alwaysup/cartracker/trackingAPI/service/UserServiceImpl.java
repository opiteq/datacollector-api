package com.alwaysup.cartracker.trackingAPI.service;

import com.alwaysup.cartracker.trackingAPI.model.User;
import com.alwaysup.cartracker.trackingAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(int page, int pageSize) {
        Page<User> allUsers = userRepository.findAll(PageRequest.of(page, pageSize));
        return allUsers;
    }

    public User addUser(String username, String email) {
        User user = userRepository.save(new User(username, email));
        return user;
    }
}
