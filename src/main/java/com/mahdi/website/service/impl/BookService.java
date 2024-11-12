package com.mahdi.website.service.impl;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.exception.book.BookNotFoundException;
import com.mahdi.website.mapper.BookMapper;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.model.Book;
import com.mahdi.website.repository.BookRepository;
import com.mahdi.website.repository.BookSearchSpecification;
import com.mahdi.website.service.interfaces.BookServiceInterface;
import com.mahdi.website.service.validation.interfaces.BookValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceInterface {

    private final BookMapper bookMapper;
    private final PublisherMapper publisherMapper;
    private final BookValidation bookValidation;
    private final BookRepository bookRepository;


    @Override
    public List<Book> searchBook(BookDTO bookDTO) {
        BookSearchSpecification  specification = new BookSearchSpecification(bookDTO);
        return bookRepository.findAll(specification);
    }

    @Override
    public Book saveBook(BookDTO bookDTO) {
        bookValidation.bookValidation(bookDTO);
        Book book = bookMapper.toEntity(bookDTO);
        book.setActive(Boolean.TRUE);
        return bookRepository.save(book);
    }

    @Override
    public BookDTO findBookDTOById(Long id) {
        return bookMapper.toDTO(findBookById(id));
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book deactivateBookByBookId(String BookId) {
        Book book = bookRepository.findBookByBookId(BookId).orElseThrow(BookNotFoundException::new);
        book.setActive(Boolean.FALSE);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(BookDTO bookDTO) {
        Book book = findBookById(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setActive(bookDTO.getActive());
        book.setPublishYear(bookDTO.getPublishYear());
        book.setPublishDate(bookDTO.getPublishDate());
        book.setPublisher(publisherMapper.toEntity(bookDTO.getPublisher()));
        return bookRepository.save(book);
    }
}
