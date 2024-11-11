package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.model.Author;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> searchAuthorDTO(AuthorDTO authorDTO);
    List<Author> searchAuthor(AuthorDTO authorDTO);
    Author saveAuthor(AuthorDTO authorDTO);
    AuthorDTO saveAuthorDTO(AuthorDTO authorDTO);
    Author findAuthorByFirstName(String firstName);
    Author findAuthorBylastName(String lastName);
    Author findAuthorByEmail(String email);
    Author findAuthorByPhoneNumber(String phoneNumber);
    AuthorDTO findAuthorDTOById(Long id);
    Author findAuthorById(Long id);
    AuthorDTO deactivateAuthorDTO(AuthorDTO authorDTO);

    void updateAuthor(AuthorDTO authorDTO);
}
