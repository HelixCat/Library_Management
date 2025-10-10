package com.mahdi.website.service.validation.impl;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.exception.user.DuplicateEmailException;
import com.mahdi.website.exception.user.DuplicateNationalCodeException;
import com.mahdi.website.exception.user.DuplicatePhoneNumberException;
import com.mahdi.website.exception.user.DuplicateUserNameException;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.UserRepository;
import com.mahdi.website.service.validation.interfaces.SignUpValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpValidation implements SignUpValidationInterface {

    private final UserRepository userRepository;

    @Override
    public void userNameValidation(String userName) {
        Optional<User> user = userRepository.findUserByUsername(userName);
        if (user.isPresent()) {
            throw new DuplicateUserNameException();
        }
    }

    @Override
    public void emailValidation(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public void phoneNumberValidation(String phoneNumber) {
        Optional<User> user = userRepository.findUserByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            throw new DuplicatePhoneNumberException();
        }
    }

    @Override
    public void nationalCodeValidation(String nationalCode) {
        Optional<User> user = userRepository.findUserByNationalCode(nationalCode);
        if (user.isPresent()) {
            throw new DuplicateNationalCodeException();
        }
    }

    @Override
    public void signUpValidation(UserDTO userDTO) {
        userNameValidation(userDTO.getUsername());
        emailValidation(userDTO.getEmail());
        phoneNumberValidation(userDTO.getPhoneNumber());
        nationalCodeValidation(userDTO.getNationalCode());
    }
}
