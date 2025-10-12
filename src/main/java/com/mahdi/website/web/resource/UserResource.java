package com.mahdi.website.web.resource;

import com.mahdi.website.web.remote.UserRemote;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.ResponseUserDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.ResponseUserMapper;
import com.mahdi.website.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/user-management")
@Tag(name = "User", description = "User management operations")
public class UserResource implements UserRemote {

    private final ResponseUserMapper responseUserMapper;
    private final UserService userService;

    @Override
    @Operation(summary = "Search users", description = "Search for users based on provided criteria")
    public ResponseEntity<List<ResponseUserDTO>> searchUsers(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.searchUserDTO(userDTO), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Save new user", description = "Register a new user in the system")
    public ResponseEntity<ResponseUserDTO> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(responseUserMapper.toDTO(userService.saveUser(userDTO)), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update user", description = "Update an existing user's information")
    public ResponseEntity<ResponseUserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(userService.updateUserDTO(userDTO), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Change password", description = "Change user's password")
    public ResponseEntity<ResponseUserDTO> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        return new ResponseEntity<>(userService.updateUserDTOPassword(changePasswordDTO), HttpStatus.OK);
    }
 
    @Override
    @Operation(summary = "Find user by ID", description = "Retrieve a user by their ID")
    public ResponseEntity<ResponseUserDTO> findUserById(@RequestBody UserDTO userDTO) throws Exception {
        return new ResponseEntity<>(responseUserMapper.toDTO(userService.findById(userDTO)), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Find user by Email", description = "Retrieve a user by their Email")
    public ResponseEntity<ResponseUserDTO> findUserByEmail(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.findUserDTOByEmail(userDTO), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "deactivate user by username", description = "deactivate a user by their username")
    public ResponseEntity<ResponseUserDTO> deactivateUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.deactivateUserDTO(userDTO), HttpStatus.OK);
    }
}
