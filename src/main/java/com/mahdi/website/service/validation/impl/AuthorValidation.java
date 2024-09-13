package com.mahdi.website.service.validation.impl;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.exception.author.DuplicateAuthorEmailException;
import com.mahdi.website.exception.author.DuplicateAuthorNationalCodeException;
import com.mahdi.website.exception.author.DuplicateAuthorPhoneNumberException;
import com.mahdi.website.model.Author;
import com.mahdi.website.repository.AuthorRepository;
import com.mahdi.website.service.validation.interfaces.AuthorValidationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorValidation implements AuthorValidationInterface {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorValidation(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void authorEmailValidation(String email) {
        Optional<Author> author = authorRepository.findAuthorByEmail(email);
        if (author.isPresent()) {
            throw new DuplicateAuthorEmailException("this email is taken by other authors");
        }
    }

    public void authorPhoneNumberValidation(String phoneNumber) {
        Optional<Author> author = authorRepository.findAuthorByPhoneNumber(phoneNumber);
        if (author.isPresent()) {
            throw new DuplicateAuthorPhoneNumberException("this phone number is taken by other authors");
        }
    }

    public void authorNationalCodeValidation(String nationalCode) {
        Optional<Author> author = authorRepository.findAuthorByNationalCode(nationalCode);
        if (author.isPresent()) {
            throw new DuplicateAuthorNationalCodeException("this National code is taken by other authors");
        }
    }

    @Override
    public void addAuthorValidation(AuthorDTO authorDTO) {
        authorEmailValidation(authorDTO.getEmail());
        authorPhoneNumberValidation(authorDTO.getPhoneNumber());
        authorNationalCodeValidation(authorDTO.getNationalCode());
    }

    @Override
    public void updateAuthorValidation(Author author, AuthorDTO authorDTO) {
        if (!authorDTO.getEmail().equals(author.getEmail())) {
            authorEmailValidation(authorDTO.getEmail());
        }
        if (!authorDTO.getPhoneNumber().equals(author.getPhoneNumber())) {
            authorPhoneNumberValidation(authorDTO.getPhoneNumber());
        }
        if (!authorDTO.getNationalCode().equals(author.getNationalCode())) {
            authorNationalCodeValidation(authorDTO.getNationalCode());
        }
    }

}
