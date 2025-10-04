package com.mahdi.website.service.validation.impl;

import com.mahdi.website.exception.global.IncorrectPasswordException;
import com.mahdi.website.service.validation.interfaces.PasswordValidationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidationService implements PasswordValidationInterface {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordValidationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void isValidPassword(String plainPassword, String hashedPassword, String context) {
        if (!passwordEncoder.matches(plainPassword, hashedPassword)) {
            throw new IncorrectPasswordException();
        }
    }

}
