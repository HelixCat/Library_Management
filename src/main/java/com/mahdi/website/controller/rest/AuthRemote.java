package com.mahdi.website.controller.rest;


import com.mahdi.website.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication", description = "Authentication APIs")
public interface AuthRemote {
    @Operation(summary = "Sign in", description = "Authenticate user and generate session/token")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Authentication successful"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/signing")
    ResponseEntity<UserDTO> signing(@Valid @RequestBody UserDTO userDTO) throws Exception;

    @Operation(summary = "Sign up", description = "Register a new user account")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Username already exists")
    })
    @PostMapping("/signup")
    ResponseEntity<UserDTO> signup(@Valid @RequestBody UserDTO userDTO) throws Exception;

    @Operation(summary = "Sign out", description = "Sign out the current user and invalidate their session")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully signed out"),
        @ApiResponse(responseCode = "401", description = "Not authenticated")
    })
    @PostMapping("/sign out")
    ResponseEntity<UserDTO> signOut();
}
