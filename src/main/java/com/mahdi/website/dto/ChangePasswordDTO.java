package com.mahdi.website.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class ChangePasswordDTO implements Serializable {

    private String userName;
    private String oldPassword;
    private String newPassword;
}
