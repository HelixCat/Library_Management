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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceInterface {

    private final BookMapper bookMapper;
    private final PublisherMapper publisherMapper;
    private final BookValidation bookValidation;
    private final BookRepository bookRepository;


    @Override
    public List<BookDTO> searchBook(BookDTO bookDTO) {
        BookSearchSpecification  specification = new BookSearchSpecification(bookDTO);
        List<Book> bookList = bookRepository.findAll(specification);
        return prepareBookList(bookList);
    }

    @Override
    public Book saveBook(BookDTO bookDTO) {
        bookValidation.bookValidation(bookDTO);
        Book book = bookMapper.toEntity(bookDTO);
        book.setActive(Boolean.TRUE);
        return bookRepository.save(book);
    }

    @Override
    public Book findBookByBookTitle(String title) {
        return bookRepository.findBookByBookTitle(title)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book findBookByBookId(String bookId) {
        return bookRepository.findBookByBookId(bookId)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book findBookByPublishYear(String publishYear) {
        return bookRepository.findBookByPublishYear(publishYear)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book findByBookByPublishDate(String publisherDate) {
        return bookRepository.findByBookByPublishDate(publisherDate)
                .orElseThrow(BookNotFoundException::new);
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
    public void deactivateBookById(Long id) {
        Book book = findBookById(id);
        book.setActive(Boolean.FALSE);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookDTO bookDTO) {
        Book book = findBookById(id);
        book.setTitle(bookDTO.getTitle());
        book.setActive(bookDTO.getActive());
        book.setPublishYear(bookDTO.getPublishYear());
        book.setPublishDate(bookDTO.getPublishDate());
        book.setPublisher(publisherMapper.toEntity(bookDTO.getPublisher()));
        bookRepository.save(book);
    }

    private List<BookDTO> prepareBookList(List<Book> bookList) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList) {
            BookDTO bookDTO = bookMapper.toDTO(book);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }
}
