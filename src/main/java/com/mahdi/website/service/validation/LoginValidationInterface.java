package com.mahdi.website.service.validation;

public interface LoginValidationInterface {

    Boolean isValidPassword(String plainPassword, String hashedPassword);

}
