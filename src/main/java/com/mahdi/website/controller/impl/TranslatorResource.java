package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.TranslatorRemote;
import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.mapper.TranslatorMapper;
import com.mahdi.website.service.interfaces.TranslatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("translator-management")
public class TranslatorResource implements TranslatorRemote {

    private final TranslatorMapper translatorMapper;
    private final TranslatorService translatorService;

    @Override
    public ResponseEntity<List<TranslatorDTO>> searchTranslators(@RequestBody TranslatorDTO translatorDTO) {
        List<TranslatorDTO> translatorDTOList = translatorService.searchTranslator(translatorDTO);
        return new ResponseEntity<>(translatorDTOList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<TranslatorDTO> saveTranslator(@RequestBody TranslatorDTO translatorDTO) {
        return new ResponseEntity<>(translatorMapper.toDTO(translatorService.saveTranslator(translatorDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TranslatorDTO> deactivateTranslator(@RequestBody TranslatorDTO translatorDTO) {
        return new ResponseEntity<>(translatorMapper.toDTO(translatorService.deactivateTranslatorById(translatorDTO.getId())), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TranslatorDTO> updateTranslator(@RequestBody TranslatorDTO translatorDTO) {
        return new ResponseEntity<>(translatorMapper.toDTO(translatorService.updateTranslator(translatorDTO)), HttpStatus.OK);
    }
}
