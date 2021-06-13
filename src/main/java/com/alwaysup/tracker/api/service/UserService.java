package com.alwaysup.tracker.api.service;

import com.alwaysup.tracker.api.model.User;
import com.alwaysup.tracker.api.model.dto.UserDTO;

public interface UserService {

    UserDTO getUserDtoByUsername(String username);

    UserDTO addUserFromDTO(UserDTO userDto);

    UserDTO updateUserFromDTO(UserDTO userDto);

    boolean deleteUserFromDTO(UserDTO userDto);

    User getUserByUsername(String username);
}
