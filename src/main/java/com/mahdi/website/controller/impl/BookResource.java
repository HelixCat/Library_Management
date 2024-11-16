package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.BookRemote;
import com.mahdi.website.dto.BookDTO;

import com.mahdi.website.mapper.BookMapper;
import com.mahdi.website.service.interfaces.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/book-management")
public class BookResource implements BookRemote {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @Override
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper.toDTOList(bookService.searchBook(bookDTO)), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper.toDTO(bookService.saveBook(bookDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookDTO> deactivateBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper.toDTO(bookService.deactivateBookByBookId(bookDTO.getBookId())), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper.toDTO(bookService.updateBook(bookDTO)), HttpStatus.OK);
    }
}
