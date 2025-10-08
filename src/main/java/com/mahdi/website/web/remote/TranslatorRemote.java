package com.mahdi.website.web.remote;

import com.mahdi.website.dto.TranslatorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Translator", description = "Translator management APIs")
public interface TranslatorRemote {
    @Operation(summary = "Search translators", description = "Search for translators based on provided criteria")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Translators found", content = @Content(schema = @Schema(implementation = TranslatorDTO.class))),
        @ApiResponse(responseCode = "404", description = "No translators found")
    })
    @GetMapping("/search")
    ResponseEntity<List<TranslatorDTO>> searchTranslators(@ModelAttribute TranslatorDTO translatorDTO);

    @Operation(summary = "Save new translator", description = "Add a new translator to the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Translator created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Translator already exists")
    })
    @PostMapping("/save")
    ResponseEntity<TranslatorDTO> saveTranslator(@RequestBody TranslatorDTO translatorDTO);

    @Operation(summary = "Update translator", description = "Update an existing translator's information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Translator updated successfully"),
        @ApiResponse(responseCode = "404", description = "Translator not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/update")
    ResponseEntity<TranslatorDTO> updateTranslator(@RequestBody TranslatorDTO translatorDTO);

    @Operation(summary = "Find translator by ID", description = "Retrieve a translator by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Translator found"),
        @ApiResponse(responseCode = "404", description = "Translator not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<TranslatorDTO> findTranslatorById(@PathVariable Long id);
}
