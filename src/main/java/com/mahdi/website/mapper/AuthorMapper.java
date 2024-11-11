package com.mahdi.website.mapper;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.model.Author;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toAuthor(AuthorDTO authorDTO);

    AuthorDTO toAuthorDTO(Author author);

    List<AuthorDTO> toAuthorDTOS(List<Author> authors);

    List<Author> toAuthorS(List<AuthorDTO> authorDTOSs);
}
