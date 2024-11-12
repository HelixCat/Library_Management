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
    public Author saveAuthor(AuthorDTO authorDTO) {
        authorValidation.addAuthorValidation(authorDTO);
        Author author = authorMapper.toEntity(authorDTO);
        author.setActive(Boolean.TRUE);
        return authorRepository.save(author);
    }

    public AuthorDTO saveAuthorDTO(AuthorDTO authorDTO) {
        return authorMapper.toDTO(saveAuthor(authorDTO));
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public Author deactivateAuthor(AuthorDTO authorDTO) {
        Author author = findAuthorById(authorDTO.getId());
        author.setActive(Boolean.FALSE);
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(AuthorDTO authorDTO) {
        Author author = findAuthorById(authorDTO.getId());
        authorValidation.updateAuthorValidation(author, authorDTO);
        update(authorDTO, author);
        return authorRepository.save(author);
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
