package com.mahdi.website.mapper;

import java.util.List;


public interface BaseMapper<E, D>{

    E toEntity(D d);

    D toDTO(E e);

    List<E> ToEntities(List<D> DTOList);

    List<D> toDTOList(List<E> EntityList);
}
