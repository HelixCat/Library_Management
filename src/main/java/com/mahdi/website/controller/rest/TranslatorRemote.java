package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.TranslatorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TranslatorRemote {

    @GetMapping("/search")
    ResponseEntity<List<TranslatorDTO>> searchTranslators(@ModelAttribute TranslatorDTO translatorDTO);

    @PostMapping("/save")
    ResponseEntity<TranslatorDTO> saveTranslator(@RequestBody TranslatorDTO translatorDTO);

    @PutMapping( "/deactivate")
    ResponseEntity<TranslatorDTO> deactivateTranslator(@RequestBody TranslatorDTO translatorDTO);

    @PostMapping("/update")
    ResponseEntity<TranslatorDTO> updateTranslator(@RequestBody TranslatorDTO translatorDTO);
}
