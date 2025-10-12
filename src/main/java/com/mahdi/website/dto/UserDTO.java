package com.mahdi.website.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mahdi.website.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
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
