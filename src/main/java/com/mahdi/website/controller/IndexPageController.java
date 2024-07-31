package com.mahdi.website.controller;

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
}
