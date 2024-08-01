package com.mahdi.website.service.validation;

import com.mahdi.website.exeception.IncorrectPasswordExceprion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginValidation implements LoginValidationInterface{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginValidation(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void isValidPassword(String plainPassword, String hashedPassword, String context) {
        if (Boolean.FALSE.equals(passwordEncoder.matches(plainPassword, hashedPassword))) {
            throw new IncorrectPasswordExceprion("password is not correct!!!", context);
        }
    }

}
