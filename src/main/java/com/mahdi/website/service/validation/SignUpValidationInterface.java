package com.mahdi.website.service.validation;

import com.mahdi.website.dto.UserDTO;

public interface SignUpValidationInterface {

    void userNameValidation(String userName);

    void emailValidation(String email);

    void phoneNumberValidation(String phoneNumber);

    void nationalCodeValidation(String nationalCode);

    void signUpValidation(UserDTO userDTO);
}
