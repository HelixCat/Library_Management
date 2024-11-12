package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.UserRemote;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.User;
import com.mahdi.website.service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user-management")
public class UserResource implements UserRemote {

    private final UserServiceInterface userService;

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
}
