package com.mahdi.website.service.validation.impl;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.exception.author.DuplicateAuthorEmailException;
import com.mahdi.website.exception.author.DuplicateAuthorPhoneNumberException;
import com.mahdi.website.model.Author;
import com.mahdi.website.repository.AuthorRepository;
import com.mahdi.website.service.validation.interfaces.AuthorValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorValidation implements AuthorValidationInterface {

    private final AuthorRepository authorRepository;

    public void authorEmailValidation(String email) {
        Optional<Author> author = authorRepository.findAuthorByEmail(email);
        if (author.isPresent()) {
            throw new DuplicateAuthorEmailException();
        }
    }

    public void authorPhoneNumberValidation(String phoneNumber) {
        Optional<Author> author = authorRepository.findAuthorByPhoneNumber(phoneNumber);
        if (author.isPresent()) {
            throw new DuplicateAuthorPhoneNumberException();
        }
    }

    @Override
    public void addAuthorValidation(AuthorDTO authorDTO) {
        authorEmailValidation(authorDTO.getEmail());
        authorPhoneNumberValidation(authorDTO.getPhoneNumber());
    }

    @Override
    public void updateAuthorValidation(Author author, AuthorDTO authorDTO) {
        if (!authorDTO.getEmail().equals(author.getEmail())) {
            authorEmailValidation(authorDTO.getEmail());
        }
        if (!authorDTO.getPhoneNumber().equals(author.getPhoneNumber())) {
            authorPhoneNumberValidation(authorDTO.getPhoneNumber());
        }
    }

}
