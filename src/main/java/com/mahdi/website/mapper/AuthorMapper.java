package com.mahdi.website.mapper;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.model.Author;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AuthorMapper extends BaseMapper<Author, AuthorDTO>{
}
