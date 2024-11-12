package com.mahdi.website.mapper;


import com.mahdi.website.model.Publisher;
import com.mahdi.website.dto.PublisherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper extends BaseMapper<Publisher, PublisherDTO>{
}
