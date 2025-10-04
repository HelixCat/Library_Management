package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.AuthRemote;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<UserDTO> signing(@Valid @RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(userMapper.toDTO(userService.authenticateUser(userDTO)));
    }

    @Override
    @Operation(summary = "Sign up", description = "Register a new user account")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(userMapper.toDTO(userService.saveUser(userDTO)));
    }

    @Override
    @Operation(summary = "Sign out", description = "Sign out the current user and invalidate their session")
    public ResponseEntity<UserDTO> signOut() {
        userService.signout();
        return ResponseEntity.ok().build();
    }
}
