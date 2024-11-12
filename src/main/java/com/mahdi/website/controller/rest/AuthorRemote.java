package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.AuthorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AuthorRemote {

    @PostMapping("/search")
    ResponseEntity<List<AuthorDTO>> searchAuthors(@RequestBody AuthorDTO authorDTO);

    @PostMapping("/save")
    ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO authorDTO);

    @PutMapping("/deactivate")
    ResponseEntity<AuthorDTO> deactivateAuthor(@RequestBody AuthorDTO authorDTO);

    @PostMapping("/update")
    ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO);
}
