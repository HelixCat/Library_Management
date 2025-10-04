package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.AuthRemote;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication operations")
public class AuthResource implements AuthRemote {

    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    @Operation(summary = "Sign in", description = "Authenticate user and generate session/token")
    public ResponseEntity<UserDTO> signin(@Valid @RequestBody UserDTO userDTO) throws Exception {
        UserDTO user = userMapper.toDTO(userService.authenticateUser(userDTO));
        return ResponseEntity.ok(user);
    }

    @Override
    @Operation(summary = "Sign up", description = "Register a new user account")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserDTO userDTO) throws Exception {
        UserDTO user = userMapper.toDTO(userService.saveUser(userDTO));
        return ResponseEntity.ok(user);
    }
}
