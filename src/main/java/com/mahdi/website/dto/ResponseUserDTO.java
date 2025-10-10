package com.mahdi.website.dto;

import com.mahdi.website.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO implements Serializable {

    private Long id;
    private String email;
    private String gender;
    private Boolean active;
    private String birthday;
    private String manualId;
    private String username;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String nationalCode;
    private String base64ProfileImage;
    private Set<Role> roles = new HashSet<>();
}
