package com.mahdi.website.service.interfaces;


import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.ResponseUserDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {
    @Cacheable(value = "userDetails", key = "#userDTO.email", unless = "#result == null")
    ResponseUserDTO findUserDTOByEmail(UserDTO userDTO);

    List<User> searchUser(UserDTO userDTO);

    User deactivateUser(UserDTO userDTO);

    User loadUserByUserName(UserDTO userDTO);

    User findUserByEmail(UserDTO userDTO);

    User saveUser(UserDTO userDTO) throws Exception;

    User updateUser(UserDTO userDTO) throws Exception;

    User updateUserPassword(ChangePasswordDTO changePasswordDTO) throws Exception;

    User authenticateUser(UserDTO userDTO) throws Exception;

    User loadUserById(UserDTO userDTO) throws Exception;
}
