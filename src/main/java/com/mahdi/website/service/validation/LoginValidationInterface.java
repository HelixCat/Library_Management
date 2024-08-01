package com.mahdi.website.service.validation;

public interface LoginValidationInterface {

    void isValidPassword(String plainPassword, String hashedPassword, String context);

}
