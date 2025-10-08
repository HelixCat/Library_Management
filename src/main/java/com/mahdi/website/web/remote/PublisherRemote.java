package com.mahdi.website.web.remote;

import com.mahdi.website.dto.PublisherDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Publisher", description = "Publisher management APIs")
public interface PublisherRemote {
    @Operation(summary = "Search publishers", description = "Search for publishers based on provided criteria")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Publishers found", content = @Content(schema = @Schema(implementation = PublisherDTO.class))),
        @ApiResponse(responseCode = "404", description = "No publishers found")
    })
    @PostMapping("/search")
    ResponseEntity<List<PublisherDTO>> searchPublishers(@RequestBody PublisherDTO publisherDTO);

    @Operation(summary = "Save new publisher", description = "Add a new publisher to the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Publisher created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Publisher already exists")
    })
    @PostMapping("/save")
    ResponseEntity<PublisherDTO> savePublisher(@RequestBody PublisherDTO publisherDTO);

    @Operation(summary = "Update publisher", description = "Update an existing publisher's information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Publisher updated successfully"),
        @ApiResponse(responseCode = "404", description = "Publisher not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/update")
    ResponseEntity<PublisherDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO);

    @Operation(summary = "Find publisher by ID", description = "Retrieve a publisher by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Publisher found"),
        @ApiResponse(responseCode = "404", description = "Publisher not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<PublisherDTO> findPublisherById(@PathVariable Long id);
}
