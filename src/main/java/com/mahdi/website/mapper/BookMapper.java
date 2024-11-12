package com.mahdi.website.mapper;

import org.mapstruct.Mapper;
import com.mahdi.website.model.Book;
import com.mahdi.website.dto.BookDTO;


@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapper<Book, BookDTO> {

}
