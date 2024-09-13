package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.model.Book;

import java.util.List;

public interface BookServiceInterface {
    List<BookDTO> searchBook(BookDTO bookDTO);
    Book saveBook(BookDTO bookDTO);
    Book findBookByFirstName(String firstName);
    Book findBookBylastName(String lastName);
    Book findBookByEmail(String email);
    Book findBookByPhoneNumber(String phoneNumber);
    BookDTO findBookDTOById(Long id);
    Book findBookById(Long id);
    void deactivateBookById(Long id);
    void updateBook(Long id, BookDTO bookDTO);
}
