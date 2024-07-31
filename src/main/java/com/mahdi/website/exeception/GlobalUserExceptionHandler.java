package com.mahdi.website.exeception;


import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.UserDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalUserExceptionHandler {

    @ExceptionHandler(UserNotFoundExcpetion.class)
    public String handleUserNotFoundException(UserNotFoundExcpetion exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("userDTO", new UserDTO());
        return "sign_in";
    }

    @ExceptionHandler(IncorrectPasswordExceprion.class)
    public String handleIncorrectPasswordException(IncorrectPasswordExceprion exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        model.addAttribute("userDTO", new UserDTO());
        return "sign_in";
    }

    @ExceptionHandler(DuplicateUserNameException.class)
    public String handleDuplicateUserNameException(DuplicateUserNameException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        UserDTO userDTO = new UserDTO();
        AddressDTO addressDTO = new AddressDTO();
        userDTO.setAddressDTO(addressDTO);
        model.addAttribute("userDTO",userDTO);
        return "signup";
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public String handleDuplicateEmailException(DuplicateEmailException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        UserDTO userDTO = new UserDTO();
        AddressDTO addressDTO = new AddressDTO();
        userDTO.setAddressDTO(addressDTO);
        model.addAttribute("userDTO",userDTO);
        return "signup";
    }

    @ExceptionHandler(DuplicateNationalCodeException.class)
    public String handleDuplicateNationalCodeException(DuplicateNationalCodeException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        UserDTO userDTO = new UserDTO();
        AddressDTO addressDTO = new AddressDTO();
        userDTO.setAddressDTO(addressDTO);
        model.addAttribute("userDTO",userDTO);
        return "signup";
    }

    @ExceptionHandler(DuplicatePhoneNumberException.class)
    public String handleDuplicatePhoneNumberException(DuplicatePhoneNumberException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        UserDTO userDTO = new UserDTO();
        AddressDTO addressDTO = new AddressDTO();
        userDTO.setAddressDTO(addressDTO);
        model.addAttribute("userDTO",userDTO);
        return "signup";
    }
}
