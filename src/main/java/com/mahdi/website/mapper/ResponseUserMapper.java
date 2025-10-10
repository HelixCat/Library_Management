package com.mahdi.website.mapper;

import com.mahdi.website.dto.ResponseUserDTO;
import com.mahdi.website.enumeration.Role;
import com.mahdi.website.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ResponseUserMapper extends BaseMapper<User, ResponseUserDTO> {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRoles")
    ResponseUserDTO toDTO(User user);

    @Named("mapRoles")
    default Set<Role> mapRoles(Set<Role> roles) {
        return roles != null ? new HashSet<>(roles) : new HashSet<>();
    }
}
