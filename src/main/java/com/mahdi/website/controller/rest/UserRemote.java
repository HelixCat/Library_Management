package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserRemote {
    @PostMapping("/save")
    ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) throws Exception;

    @PutMapping("/update")
    ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception;

    @GetMapping("/find")
    ResponseEntity<UserDTO> findUserByUserName(@RequestBody UserDTO userDTO);

    @PostMapping("/change-password")
    ResponseEntity<UserDTO> updateUserPassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws Exception;
}
