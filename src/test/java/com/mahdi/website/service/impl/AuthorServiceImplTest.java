package com.mahdi.website.service.impl;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.exception.author.AuthorNotFoundException;
import com.mahdi.website.model.Author;
import com.mahdi.website.repository.AuthorRepository;
import com.mahdi.website.mapper.AuthorMapper;
import com.mahdi.website.service.validation.interfaces.AuthorValidationInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;
    @Mock
    private AuthorValidationInterface authorValidation;
    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAuthorById_found() {
        Author author = new Author();
        author.setId(1L);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        Author result = authorServiceImpl.findAuthorById(authorDTO);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void findAuthorById_notFound() {
        when(authorRepository.findById(2L)).thenReturn(Optional.empty());
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(2L);
        assertThrows(AuthorNotFoundException.class, () -> authorServiceImpl.findAuthorById(authorDTO));
        verify(authorRepository, times(1)).findById(2L);
    }
}

