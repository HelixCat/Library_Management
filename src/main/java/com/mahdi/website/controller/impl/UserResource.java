package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.UserRemote;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.User;
import com.mahdi.website.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user-management")
public class UserResource implements UserRemote {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        User user = userService.saveUser(userDTO);
        return new ResponseEntity<>(userService.prepareToUserDTO(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception {
        User user = userService.updateUser(userDTO);
        return new ResponseEntity<>(userService.prepareToUserDTO(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> findUserByUserName(@RequestBody UserDTO userDTO) {
        User user = userService.loadUserByUserName(userDTO.getUsername());
        return new ResponseEntity<>(userService.prepareToUserDTO(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> updateUserPassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws Exception {
        User user = userService.updateUserPassword(changePasswordDTO);
        return new ResponseEntity<>(userService.prepareToUserDTO(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> deactivateUser(@RequestBody UserDTO userDTO) {
        User user = userService.deactivateUser(userDTO);
        return new ResponseEntity<>(userService.prepareToUserDTO(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDTO>> searchUser(@RequestBody UserDTO userDTO) {
        List<UserDTO> userDTOS = userService.searchUser(userDTO);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
}
