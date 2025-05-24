package com.mahdi.website.service.interfaces;


import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> searchUser(UserDTO userDTO);
    UserDTO prepareToUserDTO(User user);
    User deactivateUser(UserDTO userDTO);
    User loadUserByUserName(String userName);
    User loadUserByEmail(String email);
    User saveUser(UserDTO userDTO) throws Exception;
    User updateUser(UserDTO userDTO) throws Exception;
    User updateUserPassword(ChangePasswordDTO changePasswordDTO) throws Exception;
}
