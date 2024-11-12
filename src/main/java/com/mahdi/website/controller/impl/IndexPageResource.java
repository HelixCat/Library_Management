package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.IndexRemote;
import com.mahdi.website.dto.UserDTO;

import com.mahdi.website.service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/index-management")
public class IndexPageResource implements IndexRemote {

    private final UserServiceInterface userService;

    @Override
    public ResponseEntity login(UserDTO userDTO) {
        userService.loadUserByUserName(userDTO.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
