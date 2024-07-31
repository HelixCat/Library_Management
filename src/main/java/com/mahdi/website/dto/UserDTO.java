package com.mahdi.website.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO implements Serializable {

    private Long id;
    private String fullName;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nationalCode;
    private Boolean manager;
    private String password;
    private String job;
    private byte[] image;
    private String base64ProfileImage;
    private AddressDTO addressDTO;
}
