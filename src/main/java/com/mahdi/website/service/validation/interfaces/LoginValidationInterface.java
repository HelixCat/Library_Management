package com.mahdi.website.service.validation.interfaces;

public interface LoginValidationInterface {

    void isValidPassword(String plainPassword, String hashedPassword, String context);

}
