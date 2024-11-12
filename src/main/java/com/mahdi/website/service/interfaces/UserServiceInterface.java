package com.mahdi.website.service.interfaces;


import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(UserDTO userDTO) throws Exception;
    List<Address> saveAddress(List<Address> addressList);
    User loadUserByEmail(String email) throws Exception;
    User loadUserByUserName(String userName);
    User updateUserPassword(ChangePasswordDTO changePasswordDTO) throws Exception;
    User updateUser(UserDTO userDTO) throws Exception;
}
