package com.mahdi.website.service.impl;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.exception.book.BookNotFoundException;
import com.mahdi.website.model.Book;
import com.mahdi.website.repository.BookRepository;
import com.mahdi.website.repository.BookSearchSpecification;
import com.mahdi.website.service.interfaces.BookServiceInterface;
import com.mahdi.website.service.validation.interfaces.BookValidationInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final BookValidationInterface bookValidation;

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper, BookValidationInterface bookValidation) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.bookValidation = bookValidation;
    }

    @Override
    public List<BookDTO> searchBook(BookDTO bookDTO) {
        BookSearchSpecification  specification = new BookSearchSpecification(bookDTO);
        List<Book> bookList = bookRepository.findAll(specification);
        return prepareBookList(bookList);
    }

    @Override
    public Book saveBook(BookDTO bookDTO) {
        bookValidation.addBookValidation(bookDTO);
        Book book = prepareBook(bookDTO);
        return bookRepository.save(book);
    }

    @Override
    public Book findBookByFirstName(String firstName) {
        return bookRepository.findByBookByFirstName(firstName)
                .orElseThrow(() -> new BookNotFoundException("book with name " + firstName + " does not exist"));
    }

    @Override
    public Book findBookBylastName(String lastName) {
        return bookRepository.findByBookByLastName(lastName)
                .orElseThrow(() -> new BookNotFoundException("book with name " + lastName + " does not exist"));
    }

    @Override
    public Book findBookByEmail(String email) {
        return bookRepository.findBookByEmail(email)
                .orElseThrow(() -> new BookNotFoundException("book with email " + email + " does not exist"));
    }

    @Override
    public Book findBookByPhoneNumber(String phoneNumber) {
        return bookRepository.findBookByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new BookNotFoundException("book with phone number " + phoneNumber + " does not exist"));
    }

    @Override
    public BookDTO findBookDTOById(Long id) {
        Book book = findBookById(id);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("book with id " + id + " does not exist"));
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
        bookValidation.updateBookValidation(book, bookDTO);
        book.setFirstName(bookDTO.getFirstName());
        book.setLastName(bookDTO.getLastName());
        book.setEmail(bookDTO.getEmail());
        book.setPhoneNumber(bookDTO.getPhoneNumber());
        book.setActive(bookDTO.getActive());
        bookRepository.save(book);
    }


    private Book prepareBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        book.setActive(Boolean.TRUE);
        return book;
    }

    private List<BookDTO> prepareBookList(List<Book> bookList) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList) {
            BookDTO bookDTO = prepareBookDTO(book);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    private BookDTO prepareBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
