package com.mahdi.website.service;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAllUsers();
    void saveUser(UserDTO userDTO) throws Exception;

    List<Address> saveAddress(List<Address> addressList);

    UserDTO AddAddressToTheUser(String username, AddressDTO addressDTO);

    User loadUserByEmail(String email);
    User loadUserByUserName(String userName);
    UserDTO loadUserDTOByUserName(String userName);
    UserDTO loadUserDTOByEmail(String email);
    void updateUser(String userName, UserDTO userDTO) throws Exception;
    void updateUserPassword(String username, ChangePasswordDTO changePasswordDTO) throws Exception;
    Boolean isValidPassword(String plainPassword, String hashedPassword);
}
