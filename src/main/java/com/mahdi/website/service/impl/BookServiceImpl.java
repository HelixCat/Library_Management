package com.mahdi.website.service.impl;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.exception.book.BookNotFoundException;
import com.mahdi.website.mapper.BookMapper;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.model.Book;
import com.mahdi.website.repository.BookRepository;
import com.mahdi.website.repository.BookSearchSpecification;
import com.mahdi.website.service.interfaces.BookService;
import com.mahdi.website.service.validation.interfaces.BookValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final PublisherMapper publisherMapper;
    private final BookValidation bookValidation;
    private final BookRepository bookRepository;


    @Override
    @Cacheable(value = "bookSearch", key = "T(java.util.Objects).hash(#bookDTO.title, #bookDTO.authors, #bookDTO.publisher, #bookDTO.active)", unless = "#result == null or #result.isEmpty()")
    public List<Book> searchBook(BookDTO bookDTO) {
        BookSearchSpecification specification = new BookSearchSpecification(bookDTO);
        return bookRepository.findAll(specification);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "bookDetails", key = "#result.bookId")
    }, evict = {
            @CacheEvict(value = "bookSearch", allEntries = true)
    })
    public Book saveBook(BookDTO bookDTO) {
        bookValidation.bookValidation(bookDTO);
        Book book = bookMapper.toEntity(bookDTO);
        book.setActive(Boolean.TRUE);
        return bookRepository.save(book);
    }

    @Override
    @Cacheable(value = "bookDetails", key = "#id", unless = "#result == null")
    public BookDTO findBookDTOById(Long id) {
        return bookMapper.toDTO(findBookById(id));
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "bookDetails", key = "#result.bookId")
    }, evict = {
            @CacheEvict(value = "bookSearch", allEntries = true)
    })
    public Book deactivateBookByBookId(String BookId) {
        Book book = bookRepository.findBooksByBookId(BookId).orElseThrow(BookNotFoundException::new);
        book.setActive(Boolean.FALSE);
        return bookRepository.save(book);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "bookDetails", key = "#result.bookId")
    }, evict = {
            @CacheEvict(value = "bookSearch", allEntries = true)
    })
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
