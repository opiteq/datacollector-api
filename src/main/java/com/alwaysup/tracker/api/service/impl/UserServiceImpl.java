package com.alwaysup.tracker.api.service.impl;

import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.model.User;
import com.alwaysup.tracker.api.repository.UserRepository;
import com.alwaysup.tracker.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(int page, int pageSize) {
        List<User> allUsers = userRepository.findAll(PageRequest.of(page, pageSize));
        return allUsers;
    }

    public User getUser(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user;
    }

    public User addUser(String username, String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = userRepository.save(new User(username, email));
            return user;
        }
        return null;
    }

    public User deleteUser(long uid) {
        User user = userRepository.findById(uid).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    public User updateUserName(User user, String name) {
        user.setUsername(name);
        user = userRepository.save(user);
        return user;
    }

    public User updateUserEmail(User user, String email) {
        user.setEmail(email);
        user = userRepository.save(user);
        return user;
    }

    public User addDevice(User user, Device device) {
        Set<Device> userDevices = user.getUserDevices();
        userDevices.add(device);
        user.setUserDevices(userDevices);
        userRepository.save(user);
        return user;
    }

    public User removeDevice(User user, Device device) {
        Set<Device> userDevices = user.getUserDevices();
        userDevices.remove(device);
        user.setUserDevices(userDevices);
        userRepository.save(user);
        return user;
    }
}
