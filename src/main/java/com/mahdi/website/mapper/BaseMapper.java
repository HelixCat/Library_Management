package com.mahdi.website.mapper;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.model.Publisher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BaseMapper<E, D>{

    E toEntity(D d);

    D toDTO(E e);

    List<E> ToEntities(List<D> DTOList);

    List<D> toDTOList(List<E> EntityList);
}
