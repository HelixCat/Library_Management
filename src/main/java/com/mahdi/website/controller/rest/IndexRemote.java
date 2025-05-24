package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IndexRemote {
    @PostMapping("/sign-in")
    ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO);
}
