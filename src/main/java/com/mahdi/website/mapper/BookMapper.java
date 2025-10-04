package com.mahdi.website.mapper;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.model.Book;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapper<Book, BookDTO> {

}
