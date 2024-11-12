package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.UserRemote;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user-management")
public class UserResource implements UserRemote {

    private final UserMapper userMapper;
    private final UserServiceInterface userService;

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userMapper.toDTO(userService.saveUser(userDTO)), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UserDTO> updateUserProfile(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userMapper.toDTO(userService.updateUser(userDTO)), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<UserDTO> findUserByUserName(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userMapper.toDTO(userService.loadUserByUserName(userDTO.getUsername())), HttpStatus.OK);

    }

    @PostMapping("/change-password")
    public ResponseEntity<UserDTO> updateUserPassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws Exception {
        return new ResponseEntity<>(userMapper.toDTO(userService.updateUserPassword(changePasswordDTO)), HttpStatus.OK);
    }
}
