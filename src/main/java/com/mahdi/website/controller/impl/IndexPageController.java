package com.mahdi.website.controller.impl;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;

import com.mahdi.website.service.interfaces.UserServiceInterface;
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
        return "sign_in";
    }

    @GetMapping("/sign-in")
    public String showLoginUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "sign_in";
    }

    @PostMapping ("/sign-in")
    public String login(UserDTO userDTO, Model model) {
        UserDTO userDetail = userService.loadUserDTOByEmailForLoginPage(userDTO);
        model.addAttribute("userDetail", userDetail);
        return "home";
    }

    @GetMapping(value = "/profile/{username}")
    public String viewProfilePage(@PathVariable String username, Model model) {
        UserDTO userDetail = userService.loadUserDTOByUserName(username);
        model.addAttribute("userDetail", userDetail);
        return "profile";
    }

    @PostMapping("/profile/{username}")
    public String updateUserProfile(@PathVariable String username, @ModelAttribute UserDTO userDTO, Model model) throws Exception {
       UserDTO updatedUserDTO = userService.updateUser(username, userDTO);
       model.addAttribute("userDetail", updatedUserDTO);
       return "profile";
    }

    @GetMapping(value = "/profile/change-picture/{username}")
    public String viewChangeProfilePicturePage(@PathVariable String username, Model model) {
        UserDTO userDetail = userService.loadUserDTOByUserName(username);
        model.addAttribute("userDetail", userDetail);
        return "profile";
    }

    @PostMapping("/profile/change-picture/{username}")
    public String changeProfilePicturePage(@PathVariable String username, @RequestParam("profileImage") MultipartFile file, Model model) throws Exception {
        
//        model.addAttribute("userDetail", updatedUserDTO);
        return "profile";
    }


    @GetMapping("/home")
    public String viewHomePage(UserDTO userDetail, Model model) {
        UserDTO userDTO = userService.loadUserDTOByEmail(userDetail);
        model.addAttribute("userDetail", userDTO);
        return "home";
    }

    @GetMapping("/change-password/{username}")
    public String updateUserPasswordPage(@PathVariable String username, Model model) {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setUserName(username);
        model.addAttribute("changePasswordDTO", changePasswordDTO);
        return "change-password-page";
    }

    @PostMapping("/change-password-user/{username}")
    public String updateUserPassword(@PathVariable String username, ChangePasswordDTO changePasswordDTO, Model model) throws Exception {
        userService.updateUserPassword(username, changePasswordDTO);
        UserDTO userDetail = userService.loadUserDTOByUserName(username);
        model.addAttribute("userDetail", userDetail);
        return "redirect:/profile";
    }

    @GetMapping("/add-address/{username}")
    public String addAddressPage(@PathVariable String username, Model model) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUsername(username);
        model.addAttribute("addressDTO", addressDTO);
        return "add-address";
    }

    @PostMapping("/add-address-to-user/{username}")
    public String addAddress(@PathVariable String username, AddressDTO addressDTO, Model model) {
        UserDTO userDetail = userService.AddAddressToTheUser(username, addressDTO);
        model.addAttribute("userDetail", userDetail);
        return "redirect:/profile";
    }


}
