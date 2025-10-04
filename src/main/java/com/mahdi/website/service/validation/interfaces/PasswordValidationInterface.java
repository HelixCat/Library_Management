package com.mahdi.website.service.validation.interfaces;

public interface PasswordValidationInterface {

    void isValidPassword(String plainPassword, String hashedPassword, String context);

}
