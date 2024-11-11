package com.mahdi.website.service.impl;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.exception.author.AuthorNotFoundException;
import com.mahdi.website.mapper.AuthorMapper;
import com.mahdi.website.model.Author;
import com.mahdi.website.repository.AuthorRepository;
import com.mahdi.website.repository.AuthorSearchSpecification;
import com.mahdi.website.service.interfaces.AuthorService;
import com.mahdi.website.service.validation.interfaces.AuthorValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;
    private final AuthorValidationInterface authorValidation;

    @Override
    public List<Author> searchAuthor(AuthorDTO authorDTO) {
        AuthorSearchSpecification  specification = new AuthorSearchSpecification(authorDTO);
        return authorRepository.findAll(specification);
    }

    @Override
    public List<AuthorDTO> searchAuthorDTO(AuthorDTO authorDTO) {
        return authorMapper.toAuthorDTOS(searchAuthor(authorDTO));
    }

    @Override
    public Author saveAuthor(AuthorDTO authorDTO) {
        authorValidation.addAuthorValidation(authorDTO);
        Author author = authorMapper.toAuthor(authorDTO);
        author.setActive(Boolean.TRUE);
        return authorRepository.save(author);
    }

    public AuthorDTO saveAuthorDTO(AuthorDTO authorDTO) {
        return authorMapper.toAuthorDTO(saveAuthor(authorDTO));
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
        return authorMapper.toAuthorDTO(findAuthorById(id));
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("author with id " + id + " does not exist"));
    }

    @Override
    public AuthorDTO deactivateAuthorDTO(AuthorDTO authorDTO) {
        Author author = findAuthorById(authorDTO.getId());
        author.setActive(Boolean.FALSE);
        return authorMapper.toAuthorDTO(authorRepository.save(author));
    }

    @Override
    public void updateAuthor(AuthorDTO authorDTO) {
        Author author = findAuthorById(authorDTO.getId());
        authorValidation.updateAuthorValidation(author, authorDTO);
        update(authorDTO, author);
        authorRepository.save(author);
    }

    private void update(AuthorDTO authorDTO, Author author) {
        if (Objects.nonNull(authorDTO.getEmail())) {
            author.setEmail(authorDTO.getEmail());
        }
        if (Objects.nonNull(authorDTO.getActive())) {
            author.setActive(authorDTO.getActive());
        }
        if (Objects.nonNull(authorDTO.getLastName())) {
            author.setLastName(authorDTO.getLastName());
        }
        if (Objects.nonNull(authorDTO.getFirstName())) {
            author.setFirstName(authorDTO.getFirstName());
        }
        if (Objects.nonNull(authorDTO.getPhoneNumber())) {
            author.setPhoneNumber(authorDTO.getPhoneNumber());
        }
    }
}
