package com.mahdi.website.web.resource;

import com.mahdi.website.model.User;
import com.mahdi.website.service.security.CustomUserDetailsService;
import com.mahdi.website.service.security.jwt.JwtUtil;
import com.mahdi.website.web.remote.AuthRemote;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication operations")
public class AuthResource implements AuthRemote {

    private final UserMapper userMapper;
    private final UserService userService;
    private final CustomUserDetailsService UserDetailsService;
    private final JwtUtil jwtUtil;


    @Override
    @Operation(summary = "Sign in", description = "Authenticate user and generate session/token")
    public ResponseEntity<Map<String, String>> signing(@Valid @RequestBody UserDTO userDTO) throws Exception {
        User user = userService.authenticateUser(userDTO);

        UserDetails userDetails = UserDetailsService.loadUserByUsername(user.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        response.put("username", user.getUsername());
        response.put("roles", user.getRoles().toString());

        return ResponseEntity.ok(response);
    }

    @Override
    @Operation(summary = "Sign up", description = "Register a new user account")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(userMapper.toDTO(userService.saveUser(userDTO)));
    }
}
