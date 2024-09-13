package com.mahdi.website.service.impl;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.exception.author.AuthorNotFoundException;
import com.mahdi.website.model.Author;
import com.mahdi.website.repository.AuthorRepository;
import com.mahdi.website.repository.AuthorSearchSpecification;
import com.mahdi.website.service.interfaces.AuthorServiceInterface;
import com.mahdi.website.service.validation.interfaces.AuthorValidationInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements AuthorServiceInterface {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    private final AuthorValidationInterface authorValidation;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper, AuthorValidationInterface authorValidation) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
        this.authorValidation = authorValidation;
    }

    @Override
    public List<AuthorDTO> searchAuthor(AuthorDTO authorDTO) {
        AuthorSearchSpecification  specification = new AuthorSearchSpecification(authorDTO);
        List<Author> authorList = authorRepository.findAll(specification);
        return prepareAuthorList(authorList);
    }

    @Override
    public Author saveAuthor(AuthorDTO authorDTO) {
        authorValidation.addAuthorValidation(authorDTO);
        Author author = prepareAuthor(authorDTO);
        return authorRepository.save(author);
    }

    @Override
    public Author findAuthorByFirstName(String firstName) {
        return authorRepository.findByAuthorByFirstName(firstName)
                .orElseThrow(() -> new AuthorNotFoundException("author with name " + firstName + " does not exist"));
    }

    @Override
    public Author findAuthorBylastName(String lastName) {
        return authorRepository.findByAuthorByLastName(lastName)
                .orElseThrow(() -> new AuthorNotFoundException("author with name " + lastName + " does not exist"));
    }

    @Override
    public Author findAuthorByEmail(String email) {
        return authorRepository.findAuthorByEmail(email)
                .orElseThrow(() -> new AuthorNotFoundException("author with email " + email + " does not exist"));
    }

    @Override
    public Author findAuthorByPhoneNumber(String phoneNumber) {
        return authorRepository.findAuthorByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new AuthorNotFoundException("author with phone number " + phoneNumber + " does not exist"));
    }

    @Override
    public AuthorDTO findAuthorDTOById(Long id) {
        Author author = findAuthorById(id);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("author with id " + id + " does not exist"));
    }

    @Override
    public void deactivateAuthorById(Long id) {
        Author author = findAuthorById(id);
        author.setActive(Boolean.FALSE);
        authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = findAuthorById(id);
        authorValidation.updateAuthorValidation(author, authorDTO);
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setEmail(authorDTO.getEmail());
        author.setPhoneNumber(authorDTO.getPhoneNumber());
        author.setActive(authorDTO.getActive());
        authorRepository.save(author);
    }


    private Author prepareAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        author.setActive(Boolean.TRUE);
        return author;
    }

    private List<AuthorDTO> prepareAuthorList(List<Author> authorList) {
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for (Author author : authorList) {
            AuthorDTO authorDTO = prepareAuthorDTO(author);
            authorDTOList.add(authorDTO);
        }
        return authorDTOList;
    }

    private AuthorDTO prepareAuthorDTO(Author author) {
        return modelMapper.map(author, AuthorDTO.class);
    }
}
