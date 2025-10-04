package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Index", description = "Index page APIs")
public interface IndexRemote {
    @Operation(summary = "Get index page", description = "Retrieve the main index page of the application")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Index page retrieved successfully"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/")
    ResponseEntity<String> getIndexPage();

    @PostMapping("/sign-in")
    ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO);
}
