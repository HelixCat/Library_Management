package com.mahdi.website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "t_address")
public class Address extends BaseEntity {

    @Column(name = "c_country", length = 50)
    private String country;
    @Column(name = "c_province", length = 50)
    private String province;
    @Column(name = "c_city", length = 50)
    private String city;
    @NotBlank
    @NotNull
    @Column(name = "c_postal_code" ,length = 10, nullable = false)
    private String postalCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="publisher_id")
    private Publisher publisher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
