package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> searchAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> searchAuthorDTO(AuthorDTO authorDTO);

    Author saveAuthor(AuthorDTO authorDTO);

    Author findAuthorById(AuthorDTO authorDTO );

    AuthorDTO findAuthorDTOById(AuthorDTO authorDTO);

    Author deactivateAuthor(AuthorDTO authorDTO);

    AuthorDTO deactivateAuthorDTO(AuthorDTO authorDTO);

    Author updateAuthor(AuthorDTO authorDTO);
}
