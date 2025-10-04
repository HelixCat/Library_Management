package com.mahdi.website.controller.impl;

import com.mahdi.website.configuration.jwt.JwtUtil;
import com.mahdi.website.controller.rest.AuthRemote;
import com.mahdi.website.dto.AuthResponse;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Override
    @Operation(summary = "Sign in", description = "Authenticate user and generate session/token")
    public ResponseEntity<AuthResponse> signing(@Valid @RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );

        // Generate JWT
        String token = jwtUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(new AuthResponse(token, authentication.getName()));
    }

    @Override
    @Operation(summary = "Sign up", description = "Register a new user account")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(userMapper.toDTO(userService.saveUser(userDTO)));
    }
}
