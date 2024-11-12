package com.mahdi.website.exception.global;

import com.mahdi.website.dto.ExceptionDTO;
import com.mahdi.website.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ControllerAdvice
public class GlobalUserExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalUserExceptionHandler.class);

    // TODO change UserException Handler

//    @ExceptionHandler(UserNotFoundException.class)
//    public String handleUserNotFoundException(UserNotFoundException exception, Model model) {
//        model.addAttribute("errorMessage", exception.getMessage());
//        model.addAttribute("userDTO", new UserDTO());
//        return "sign_in";
//    }
//
//    @ExceptionHandler(IncorrectPasswordException.class)
//    public String handleIncorrectPasswordException(IncorrectPasswordException exception, Model model) {
//        model.addAttribute("errorMessage", exception.getMessage());
//        return null;
//    }
//
    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity<ExceptionDTO> handleDuplicateUserNameException(DuplicateUserNameException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setStatus("field");
        exceptionDTO.setMessage("this user name is invalid");
        exceptionDTO.setStatusCode(HttpStatus.NOT_ACCEPTABLE.toString());
        logger.error(exceptionDTO.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_ACCEPTABLE);
    }
//
//    @ExceptionHandler(DuplicateEmailException.class)
//    public String handleDuplicateEmailException(DuplicateEmailException exception, Model model) {
//        model.addAttribute("errorMessage", exception.getMessage());
//        UserDTO userDTO = new UserDTO();
//
//        model.addAttribute("userDTO",userDTO);
//        return "signup";
//    }
//
//    @ExceptionHandler(DuplicateNationalCodeException.class)
//    public String handleDuplicateNationalCodeException(DuplicateNationalCodeException exception, Model model) {
//        model.addAttribute("errorMessage", exception.getMessage());
//        UserDTO userDTO = new UserDTO();
//        model.addAttribute("userDTO",userDTO);
//        return "signup";
//    }
//
//    @ExceptionHandler(DuplicatePhoneNumberException.class)
//    public String handleDuplicatePhoneNumberException(DuplicatePhoneNumberException exception, Model model) {
//        model.addAttribute("errorMessage", exception.getMessage());
//        UserDTO userDTO = new UserDTO();
//        model.addAttribute("userDTO",userDTO);
//        return "signup";
//    }

//    @ExceptionHandler(Exception.class)
//    public String unHandledException(Exception exception, Model model) {
//        String errorMessage = exception.getMessage();
//        System.err.println(errorMessage);
//        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
//        return "unhandled_exception_page";
//    }
}
