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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
        AuthorSearchSpecification specification = new AuthorSearchSpecification(authorDTO);
        return authorRepository.findAll(specification);
    }

    @Override
    @Cacheable(value = "authorSearch", key = "T(java.util.Objects).hash(#authorDTO.firstName, #authorDTO.lastName, #authorDTO.email)", unless = "#result == null or #result.isEmpty()")
    public List<AuthorDTO> searchAuthorDTO(AuthorDTO authorDTO) {
        return authorMapper.toDTOList(searchAuthor(authorDTO));
    }

    @Override
    @Caching(put = {
            @CachePut(value = "authors", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "authorSearch", allEntries = true)
    })
    public Author saveAuthor(AuthorDTO authorDTO) {
        authorValidation.addAuthorValidation(authorDTO);
        Author author = authorMapper.toEntity(authorDTO);
        author.setActive(Boolean.TRUE);
        return authorRepository.save(author);
    }

    @Override
    public Author findAuthorById(AuthorDTO authorDTO) {
        return authorRepository.findById(authorDTO.getId()).orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    @Cacheable(value = "authors", key = "#authorDTO.id", unless = "#result == null")
    public AuthorDTO findAuthorDTOById(AuthorDTO authorDTO) {
        return authorMapper.toDTO(findAuthorById(authorDTO));
    }

    @Override
    public Author deactivateAuthor(AuthorDTO authorDTO) {
        Author author = findAuthorById(authorDTO);
        author.setActive(Boolean.FALSE);
        return authorRepository.save(author);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "authors", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "authorSearch", allEntries = true)
    })
    public AuthorDTO deactivateAuthorDTO(AuthorDTO authorDTO) {
        return authorMapper.toDTO(deactivateAuthor(authorDTO));
    }

    @Override
    @Caching(put = {
            @CachePut(value = "authors", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "authorSearch", allEntries = true)
    })
    public Author updateAuthor(AuthorDTO authorDTO) {
        Author author = findAuthorById(authorDTO);
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
