package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Book", description = "Book management APIs")
public interface BookRemote {
    @Operation(summary = "Search books", description = "Search for books based on provided criteria")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Books found", content = @Content(schema = @Schema(implementation = BookDTO.class))),
        @ApiResponse(responseCode = "404", description = "No books found")
    })
    @GetMapping("/search")
    ResponseEntity<List<BookDTO>> searchBooks(@RequestBody BookDTO bookDTO);

    @Operation(summary = "Save new book", description = "Add a new book to the library")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Book created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/save")
    ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO);

    @Operation(summary = "Deactivate book", description = "Deactivate a book by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Book deactivated successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/deactivate")
    ResponseEntity<BookDTO> deactivateBook(@RequestBody BookDTO bookDTO);

    @Operation(summary = "Update book", description = "Update an existing book's information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book updated successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/update")
    ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO);
}
