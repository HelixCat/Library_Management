package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.AuthorRemote;
import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.mapper.AuthorMapper;
import com.mahdi.website.service.interfaces.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/author-management")
public class AuthorResource implements AuthorRemote {

    private final AuthorMapper authorMapper;
    private final AuthorService authorService;

    @Override
    public ResponseEntity<List<AuthorDTO>> searchAuthors(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorMapper.toDTOList(authorService.searchAuthor(authorDTO)), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorMapper.toDTO(authorService.saveAuthor(authorDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthorDTO> deactivateAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorMapper.toDTO(authorService.deactivateAuthor(authorDTO)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorMapper.toDTO(authorService.updateAuthor(authorDTO)), HttpStatus.OK);
    }
}

