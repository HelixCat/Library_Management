package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.AuthorRemote;
import com.mahdi.website.dto.AuthorDTO;

import com.mahdi.website.mapper.AuthorMapper;
import com.mahdi.website.service.interfaces.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/author")
public class AuthorResource implements AuthorRemote {

    private final AuthorMapper authorMapper;
    private final AuthorService authorService;

    @Override
    public List<AuthorDTO> searchAuthors(@RequestBody AuthorDTO authorDTO) {
        return authorService.searchAuthorDTO(authorDTO);
    }

    @Override
    public AuthorDTO saveAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.saveAuthorDTO(authorDTO);
    }

    @Override
    public AuthorDTO deactivateAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.deactivateAuthorDTO(authorDTO);
    }

    @Override
    public AuthorDTO updateAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorMapper.toDTO(authorService.updateAuthor(authorDTO));
    }
}

