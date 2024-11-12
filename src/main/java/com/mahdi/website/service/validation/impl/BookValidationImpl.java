package com.mahdi.website.service.validation.impl;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.exception.book.DuplicateBookIdException;
import com.mahdi.website.model.Book;
import com.mahdi.website.repository.BookRepository;
import com.mahdi.website.service.validation.interfaces.BookValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookValidationImpl implements BookValidation {

    private final BookRepository bookRepository;

    @Override
    public void bookValidation(BookDTO bookDTO) {
        Optional<Book> optionalBook =  bookRepository.findBookByBookId(bookDTO.getBookId());
        if (optionalBook.isPresent()) {
            throw new DuplicateBookIdException();
        }
    }
}
