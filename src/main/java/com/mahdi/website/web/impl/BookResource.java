package com.mahdi.website.web.impl;

import com.mahdi.website.web.remote.BookRemote;
import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.mapper.BookMapper;
import com.mahdi.website.service.interfaces.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/book-management")
@Tag(name = "Book", description = "Book management operations")
public class BookResource implements BookRemote {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @Override
    @Operation(summary = "Search books", description = "Search for books based on provided criteria")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper.toDTOList(bookService.searchBook(bookDTO)), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Save new book", description = "Add a new book to the library")
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper.toDTO(bookService.saveBook(bookDTO)), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Deactivate book", description = "Deactivate a book by its ID")
    public ResponseEntity<BookDTO> deactivateBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper.toDTO(bookService.deactivateBookByBookId(bookDTO.getBookId())), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update book", description = "Update an existing book's information")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper.toDTO(bookService.updateBook(bookDTO)), HttpStatus.OK);
    }
}
