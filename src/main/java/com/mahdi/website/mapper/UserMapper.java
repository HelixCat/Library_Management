package com.mahdi.website.mapper;

import com.mahdi.website.model.User;
import com.mahdi.website.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
