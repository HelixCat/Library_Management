package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> searchAuthor(AuthorDTO authorDTO);

    Author saveAuthor(AuthorDTO authorDTO);

    Author findAuthorById(Long id);

    Author deactivateAuthor(AuthorDTO authorDTO);

    Author updateAuthor(AuthorDTO authorDTO);
}
