package com.mahdi.website.exception.global;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.exception.user.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalUserExceptionHandler {

    // TODO change UserException Handler

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("userDTO", new UserDTO());
        return "sign_in";
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public String handleIncorrectPasswordException(IncorrectPasswordException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return null;
    }

    @ExceptionHandler(DuplicateUserNameException.class)
    public String handleDuplicateUserNameException(DuplicateUserNameException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO",userDTO);
        return "signup";
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public String handleDuplicateEmailException(DuplicateEmailException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        UserDTO userDTO = new UserDTO();

        model.addAttribute("userDTO",userDTO);
        return "signup";
    }

    @ExceptionHandler(DuplicateNationalCodeException.class)
    public String handleDuplicateNationalCodeException(DuplicateNationalCodeException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO",userDTO);
        return "signup";
    }

    @ExceptionHandler(DuplicatePhoneNumberException.class)
    public String handleDuplicatePhoneNumberException(DuplicatePhoneNumberException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO",userDTO);
        return "signup";
    }

    @ExceptionHandler(Exception.class)
    public String unHandledException(Exception exception, Model model) {
        String errorMessage = exception.getMessage();
        System.err.println(errorMessage);
        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
        return "unhandled_exception_page";
    }
}
