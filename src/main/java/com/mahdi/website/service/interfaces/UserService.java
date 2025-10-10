package com.mahdi.website.service.interfaces;


import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.ResponseUserDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {

    ResponseUserDTO findUserDTOByEmail(UserDTO userDTO);

    List<User> searchUser(UserDTO userDTO);

    ResponseUserDTO updateUserDTO(UserDTO userDTO);

    User deactivateUser(UserDTO userDTO);

    User findUserByUsername(UserDTO userDTO);

    ResponseUserDTO findUserDTObyUsername(UserDTO userDTO);

    User findUserByEmail(UserDTO userDTO);

    User saveUser(UserDTO userDTO) throws Exception;

    List<ResponseUserDTO> searchUserDTO(UserDTO userDTO);

    User updateUser(UserDTO userDTO) throws Exception;

    ResponseUserDTO deactivateUserDTO(UserDTO userDTO);

    User updateUserPassword(ChangePasswordDTO changePasswordDTO) throws Exception;

    ResponseUserDTO updateUserDTOPassword(ChangePasswordDTO changePasswordDTO);

    User authenticateUser(UserDTO userDTO) throws Exception;

    User findById(UserDTO userDTO) throws Exception;
}
