package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.TranslatorRemote;
import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.mapper.TranslatorMapper;
import com.mahdi.website.service.interfaces.TranslatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/translator-management")
@Tag(name = "Translator", description = "Translator management operations")
public class TranslatorResource implements TranslatorRemote {

    private final TranslatorMapper translatorMapper;
    private final TranslatorService translatorService;

    @Override
    @Operation(summary = "Search translators", description = "Search for translators based on provided criteria")
    public ResponseEntity<List<TranslatorDTO>> searchTranslators(@ModelAttribute TranslatorDTO translatorDTO) {
        return new ResponseEntity<>(translatorMapper.toDTOList(translatorService.searchTranslator(translatorDTO)), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Save new translator", description = "Add a new translator to the system")
    public ResponseEntity<TranslatorDTO> saveTranslator(@RequestBody TranslatorDTO translatorDTO) {
        return new ResponseEntity<>(translatorMapper.toDTO(translatorService.saveTranslator(translatorDTO)), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update translator", description = "Update an existing translator's information")
    public ResponseEntity<TranslatorDTO> updateTranslator(@RequestBody TranslatorDTO translatorDTO) {
        return new ResponseEntity<>(translatorMapper.toDTO(translatorService.updateTranslator(translatorDTO)), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Find translator by ID", description = "Retrieve a translator by their ID")
    public ResponseEntity<TranslatorDTO> findTranslatorById(@PathVariable Long id) {
        return new ResponseEntity<>(translatorMapper.toDTO(translatorService.findTranslatorById(id)), HttpStatus.OK);
    }
}
