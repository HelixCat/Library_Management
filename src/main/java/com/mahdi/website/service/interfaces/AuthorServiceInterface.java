package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.model.Author;

import java.util.List;

public interface AuthorServiceInterface {
    List<AuthorDTO> searchAuthor(AuthorDTO authorDTO);
    Author saveAuthor(AuthorDTO authorDTO);
    Author findAuthorByFirstName(String firstName);
    Author findAuthorBylastName(String lastName);
    Author findAuthorByEmail(String email);
    Author findAuthorByPhoneNumber(String phoneNumber);
    AuthorDTO findAuthorDTOById(Long id);
    Author findAuthorById(Long id);
    void deactivateAuthorById(Long id);
    void updateAuthor(Long id, AuthorDTO authorDTO);
}
