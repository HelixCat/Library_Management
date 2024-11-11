package com.mahdi.website.mapper;

import org.mapstruct.Mapper;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.dto.PublisherDTO;


@Mapper(componentModel = "spring")
public interface PublisherMapper {

    Publisher toPublisher(PublisherDTO publisherDTO);

    PublisherDTO toPublisherDTO(Publisher publisher);

}
