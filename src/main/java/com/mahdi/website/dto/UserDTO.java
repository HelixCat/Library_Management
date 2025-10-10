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
public class UserDTO implements Serializable {

    private Long id;
    private String email;
    private byte[] image;
    private String gender;
    private Boolean active;
    private String birthday;
    private Integer version;
    private String manualId;
    private String username;
    private String lastName;
    private String password;
    private String firstName;
    private String phoneNumber;
    private String nationalCode;
    private Set<Role> roles = new HashSet<>();
}
