package com.mahdi.website.service.validation;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.exeception.DuplicateEmailException;
import com.mahdi.website.exeception.DuplicateNationalCodeException;
import com.mahdi.website.exeception.DuplicatePhoneNumberException;
import com.mahdi.website.exeception.DuplicateUserNameException;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SignUpValidation implements SignUpValidationInterface {

    private final UserRepository userRepository;

    @Autowired
    public SignUpValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void userNameValidation(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            throw new DuplicateUserNameException("this username is taken by other user");
        }
    }

    @Override
    public void emailValidation(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new DuplicateEmailException("this email is taken by other user");
        }
    }

    @Override
    public void phoneNumberValidation(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            throw new DuplicatePhoneNumberException("this phoneNumber is taken by other user");
        }
    }

    @Override
    public void nationalCodeValidation(String nationalCode) {
        Optional<User> user = userRepository.findByNationalCode(nationalCode);
        if (user.isPresent()) {
            throw new DuplicateNationalCodeException("this nationalCode is taken by other user");
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
