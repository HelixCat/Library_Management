package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface IndexRemote {
    @PostMapping("/sign-in")
    ResponseEntity<UserDTO> login(UserDTO userDTO);
}
