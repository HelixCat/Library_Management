package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.AuthRemote;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth-management")
public class AuthResource implements AuthRemote {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody UserDTO userDTO) throws Exception {
        UserDTO user = userMapper.toDTO(userService.saveUser(userDTO));
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDTO> signing(@Valid @RequestBody UserDTO userDTO) throws Exception {
        UserDTO user = userMapper.toDTO(userService.authenticateUser(userDTO));
        return ResponseEntity.ok(user);
    }
}

