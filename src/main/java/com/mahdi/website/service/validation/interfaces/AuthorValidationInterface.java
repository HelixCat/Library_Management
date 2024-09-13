package com.mahdi.website.service.validation.interfaces;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.model.Author;

public interface AuthorValidationInterface {
    void addAuthorValidation(AuthorDTO authorDTO);

    void updateAuthorValidation(Author author, AuthorDTO authorDTO);
}
