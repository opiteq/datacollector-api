package com.alwaysup.tracker.api.service;

import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.model.User;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;

public interface UserService {

    public List<User> getUsers(int page, int pageSize);

    public User getUser(String email);

    public User addUser(String username, String email);

    public User deleteUser(long uid);

    public User updateUserName(User user, String name);

    public User updateUserEmail(User user, String email);

    public User addDevice(User user, Device device);

    public User removeDevice(User user, Device device);

    public User updateUser(User user);
}
