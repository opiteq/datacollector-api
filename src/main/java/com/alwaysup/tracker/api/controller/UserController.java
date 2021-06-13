package com.alwaysup.tracker.api.controller;

import com.alwaysup.tracker.api.model.dto.UserDTO;
import com.alwaysup.tracker.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable("username") String username) {
        UserDTO userDto = userService.getUserDtoByUsername(username);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userDto);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto) {
        UserDTO createdUserDto = userService.addUserFromDTO(userDto);
        if (createdUserDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{username}")
                    .buildAndExpand(createdUserDto.getUsername())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdUserDto);
        }
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto) {
        UserDTO updatedUserDto = userService.updateUserFromDTO(userDto);
        if (updatedUserDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUserDto);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> removeUser(@RequestBody UserDTO userDto) {
        if (userService.deleteUserFromDTO(userDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
