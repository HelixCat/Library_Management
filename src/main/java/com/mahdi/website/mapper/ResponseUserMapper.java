package com.mahdi.website.mapper;

import com.mahdi.website.dto.ResponseUserDTO;
import com.mahdi.website.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseUserMapper extends BaseMapper<User, ResponseUserDTO> {
}
