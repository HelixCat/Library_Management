package com.mahdi.website.service.interfaces;


import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.User;

public interface UserServiceInterface {
    User saveUser(UserDTO userDTO) throws Exception;
    User loadUserByUserName(String userName);
    User updateUserPassword(ChangePasswordDTO changePasswordDTO) throws Exception;
    UserDTO prepareToUserDTO(User user);
    User updateUser(UserDTO userDTO) throws Exception;
}
