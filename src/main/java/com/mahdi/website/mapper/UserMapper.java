package com.mahdi.website.mapper;

import org.mapstruct.Mapper;
import com.mahdi.website.model.User;
import com.mahdi.website.dto.UserDTO;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
