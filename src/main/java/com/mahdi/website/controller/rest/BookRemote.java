package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookRemote {
    @GetMapping("/search")
    ResponseEntity<List<BookDTO>> searchBooks(@RequestBody BookDTO bookDTO);

    @PostMapping("/save")
    ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO);

    @PutMapping("/deactivate}")
    ResponseEntity<BookDTO> deactivateBook(@RequestBody BookDTO bookDTO);

    @PutMapping("/update")
    ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO);
}
