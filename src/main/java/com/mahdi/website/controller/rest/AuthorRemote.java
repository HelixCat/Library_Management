package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.AuthorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Author", description = "Author management APIs")
public interface AuthorRemote {
    @Operation(summary = "Search authors", description = "Search for authors based on provided criteria")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Authors found", content = @Content(schema = @Schema(implementation = AuthorDTO.class))),
        @ApiResponse(responseCode = "404", description = "No authors found")
    })
    @PostMapping("/search")
    ResponseEntity<List<AuthorDTO>> searchAuthors(@RequestBody AuthorDTO authorDTO);

    @Operation(summary = "Save new author", description = "Add a new author to the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Author created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Author already exists")
    })
    @PostMapping("/save")
    ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO authorDTO);

    @Operation(summary = "Update author", description = "Update an existing author's information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Author updated successfully"),
        @ApiResponse(responseCode = "404", description = "Author not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/update")
    ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO);

    @Operation(summary = "Find author by ID", description = "Retrieve an author by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Author found"),
        @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<AuthorDTO> findAuthorById(@PathVariable Long id);
}
