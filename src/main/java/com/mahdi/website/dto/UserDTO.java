package com.mahdi.website.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String fullName;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nationalCode;
    private Boolean admin;
    private String password;
    private String gender;
    private String FatherName;
    private String birthday;
    private byte[] image;
    private String base64ProfileImage;
    private AddressDTO addressDTO;
}
