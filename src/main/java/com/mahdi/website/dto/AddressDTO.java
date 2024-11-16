package com.mahdi.website.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private String city;
    private UserDTO user;
    private Boolean active;
    private String country;
    private Integer version;
    private String manualId;
    private String province;
    private String postalCode;
    private PublisherDTO publisher;
}
