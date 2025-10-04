package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "User management APIs")
public interface UserRemote {
    @Operation(summary = "Search users", description = "Search for users based on provided criteria")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Users found", content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "404", description = "No users found")
    })
    @GetMapping("/search")
    ResponseEntity<List<UserDTO>> searchUsers(@RequestBody UserDTO userDTO);

    @Operation(summary = "Save new user", description = "Register a new user in the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/save")
    ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) throws Exception;

    @Operation(summary = "Update user", description = "Update an existing user's information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/update")
    ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception;

    @Operation(summary = "Change password", description = "Change user's password")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Password changed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid password"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/change-password")
    ResponseEntity<UserDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws Exception;

    @Operation(summary = "Find user by ID", description = "Retrieve a user by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<UserDTO> findUserById(@PathVariable Long id);
}
