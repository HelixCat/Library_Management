package com.mahdi.website.service.impl;

import com.mahdi.website.exception.book.BookNotFoundException;
import com.mahdi.website.model.Book;
import com.mahdi.website.repository.BookRepository;
import com.mahdi.website.mapper.BookMapper;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.service.validation.interfaces.BookValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private PublisherMapper publisherMapper;
    @Mock
    private BookValidation bookValidation;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findBookById_found() {
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Book result = bookServiceImpl.findBookById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void findBookById_notFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookServiceImpl.findBookById(2L));
        verify(bookRepository, times(1)).findById(2L);
    }
}
