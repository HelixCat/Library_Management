package com.mahdi.website.mapper;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
