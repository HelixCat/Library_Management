package com.mahdi.website.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private byte[] image;
    private Boolean admin;
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
    private String base64ProfileImage;
}
