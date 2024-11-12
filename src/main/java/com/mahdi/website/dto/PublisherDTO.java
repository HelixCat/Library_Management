package com.mahdi.website.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {

    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private String manualId;
    private Integer version;
    private String phoneNumber;
}
