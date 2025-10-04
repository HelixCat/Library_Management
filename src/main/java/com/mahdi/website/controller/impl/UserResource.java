package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.UserRemote;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.model.User;
import com.mahdi.website.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user-management")
@Tag(name = "User", description = "User management operations")
public class UserResource implements UserRemote {

    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    @Operation(summary = "Search users", description = "Search for users based on provided criteria")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.searchUser(userDTO), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Save new user", description = "Register a new user in the system")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userMapper.toDTO(userService.saveUser(userDTO)), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update user", description = "Update an existing user's information")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userMapper.toDTO(userService.updateUser(userDTO)), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Change password", description = "Change user's password")
    public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws Exception {
        return new ResponseEntity<>(userMapper.toDTO(userService.updateUserPassword(changePasswordDTO)), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Find user by ID", description = "Retrieve a user by their ID")
    public ResponseEntity<UserDTO> findUserById(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userMapper.toDTO(userService.loadUserById(userDTO)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> deactivateUser(@RequestBody UserDTO userDTO) {
        User user = userService.deactivateUser(userDTO);
        return new ResponseEntity<>(userService.prepareToUserDTO(user), HttpStatus.OK);
    }
}
