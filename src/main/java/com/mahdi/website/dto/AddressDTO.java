package com.mahdi.website.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class AddressDTO implements Serializable {
    private Long id;
    private String country;
    private String province;
    private String city;
    private String postalCode;
    private String username;
}
