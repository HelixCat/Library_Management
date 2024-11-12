package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.model.Book;

import java.util.List;

public interface BookServiceInterface {
    List<BookDTO> searchBook(BookDTO bookDTO);
    Book saveBook(BookDTO bookDTO);
    Book findBookByBookTitle(String title);
    Book findBookByBookId(String bookId);
    Book findBookByPublishYear(String publisherYear);
    Book findByBookByPublishDate(String phoneNumber);
    BookDTO findBookDTOById(Long id);
    Book findBookById(Long id);
    void deactivateBookById(Long id);
    void updateBook(Long id, BookDTO bookDTO);
}
