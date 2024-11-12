package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.model.Book;

import java.util.List;

public interface BookServiceInterface {
    List<Book> searchBook(BookDTO bookDTO);
    Book saveBook(BookDTO bookDTO);
    BookDTO findBookDTOById(Long id);
    Book findBookById(Long id);
    Book deactivateBookByBookId(String BookId);
    Book updateBook(BookDTO bookDTO);
}
