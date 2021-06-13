package com.alwaysup.tracker.api.service.impl;

import com.alwaysup.tracker.api.model.User;
import com.alwaysup.tracker.api.model.dto.UserDTO;
import com.alwaysup.tracker.api.repository.UserRepository;
import com.alwaysup.tracker.api.service.DeviceService;
import com.alwaysup.tracker.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceService deviceService;

    @Override
    public UserDTO getUserDtoByUsername(String username) {
        return convertToDTO(getUserByUsername(username));
    }

    @Override
    public UserDTO addUserFromDTO(UserDTO userDto) {
        return convertToDTO(addUser(userDto.getUsername(), userDto.getEmail()));
    }

    @Override
    public UserDTO updateUserFromDTO(UserDTO userDto) {
        User user =
                userRepository.findByUsername(userDto.getUsername())
                        .orElse(null);
        if (user != null) {
            user.setEmail(userDto.getEmail());
        } else {
            user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
            if (user != null) {
                user.setUsername(userDto.getUsername());
            } else {
                return null;
            }
        }
        return userDto;
    }

    @Override
    public boolean deleteUserFromDTO(UserDTO userDto) {
        User user =
                userRepository.findByUsername(userDto.getUsername())
                        .orElse(null);
        if (user == null || !user.getEmail().equals(userDto.getEmail())) {
            return false;
        }
        deviceService.removeDevicesByUser(user);
        userRepository.delete(user);
        return true;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    private User addUser(String username, String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = userRepository.save(new User(username, email));
            return user;
        }
        return null;
    }

    private UserDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDto = new UserDTO();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
