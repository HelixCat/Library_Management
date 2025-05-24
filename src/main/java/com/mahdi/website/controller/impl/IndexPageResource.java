package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.IndexRemote;
import com.mahdi.website.dto.UserDTO;

import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth-management")
public class IndexPageResource implements IndexRemote {

    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        UserDTO user = userMapper.toDTO(userService.loadUserByEmail(userDTO.getEmail()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
