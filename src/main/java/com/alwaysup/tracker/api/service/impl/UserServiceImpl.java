package com.alwaysup.tracker.api.service.impl;

import com.alwaysup.tracker.api.model.User;
import com.alwaysup.tracker.api.repository.UserRepository;
import com.alwaysup.tracker.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(int page, int pageSize) {
        List<User> allUsers = userRepository.findAll(PageRequest.of(page, pageSize));
        return allUsers;
    }

    public User addUser(String username, String email) {
        User user = userRepository.findByEmail(email);
        User user = userRepository.save(new User(username, email));
        return user;
    }
}
