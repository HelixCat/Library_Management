package com.mahdi.website.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private Boolean active;
    private Integer version;
    private String manualId;
    private String country;
    private String province;
    private String city;
    private String postalCode;
    private String username;

}
