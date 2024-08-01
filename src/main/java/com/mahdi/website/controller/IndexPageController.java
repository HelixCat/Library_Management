package com.mahdi.website.controller;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;

import com.mahdi.website.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;

@Controller
public class IndexPageController {

    private final UserServiceInterface userService;

    @Autowired
    public IndexPageController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/signup")
    public String showSaveNewUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "signup";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute UserDTO userDTO, @RequestParam("profileImage") MultipartFile file) throws Exception {
        if (Objects.nonNull(file)) {
            userDTO.setImage(file.getBytes());
        }
        userService.saveUser(userDTO);
        return "redirect:sign_in";
    }

    @GetMapping("/sign_in")
    public String showLoginUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "sign_in";
    }

    @PostMapping ("/sign_in")
    public String login(UserDTO userDTO, Model model) {
        UserDTO userDetail = userService.loadUserDTOByEmailForLoginPage(userDTO);
        model.addAttribute("userDetail", userDetail);
        return "home";
    }

    @GetMapping(value = "/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        UserDTO userDetail = userService.loadUserDTOByUserName(username);
        model.addAttribute("userDetail", userDetail);
        return "profile";
    }

    @GetMapping("/home")
    public String returnToHome(UserDTO userDTO, Model model) {
        UserDTO userDetail = userService.loadUserDTOByEmail(userDTO);
        model.addAttribute("userDetail", userDetail);
        return "home";
    }

    @PostMapping("/change-password-user/{username}")
    public String updateUserPassword(@PathVariable String username, ChangePasswordDTO changePasswordDTO, Model model) throws Exception {
        userService.updateUserPassword(username, changePasswordDTO);
        UserDTO userDetail = userService.loadUserDTOByUserName(username);
        model.addAttribute("userDetail", userDetail);
        return "profile";
    }

    @PostMapping("/profile/{userName}")
    public String updateUserProfile(@PathVariable String userName, UserDTO userDTO) {
        try {
            userService.updateUser(userName, userDTO);
            return "redirect:/profile";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/change-password/{username}")
    public String updateUserPasswordPage(@PathVariable String username, Model model) {
        try {
            ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
            changePasswordDTO.setUserName(username);
            model.addAttribute("changePasswordDTO", changePasswordDTO);
            return "change_password_page";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add-address-to-user/{username}")
    public String addAddress(@PathVariable String username, AddressDTO addressDTO, Model model) {
        try {
            UserDTO userDetail = userService.AddAddressToTheUser(username, addressDTO);
            model.addAttribute("userDetail", userDetail);
            return "profile";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/add-address/{username}")
    public String addAddressPage(@PathVariable String username, Model model) {
        try {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setUsername(username);
            model.addAttribute("addressDTO", addressDTO);
            return "add-address";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
