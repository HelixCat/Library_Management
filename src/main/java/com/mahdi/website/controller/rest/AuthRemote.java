package com.mahdi.website.controller.rest;


import com.mahdi.website.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthRemote {

    @PostMapping("/signup")
    ResponseEntity<UserDTO> signUp(@Valid @RequestBody UserDTO userDTO) throws Exception;

    @PostMapping("/signing")
    ResponseEntity<UserDTO> signing(@Valid @RequestBody UserDTO userDTO) throws Exception;
}
