package com.mahdi.website.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "t_publisher")
public class Publisher extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(name = "c_publisher_name", nullable = false, unique = true, length = 20)
    private String name;
    @NotNull
    @NotEmpty
    @Column(name = "c_phone_number", nullable = false, unique = true, length = 11)
    private String phoneNumber;
    @NotNull
    @NotEmpty
    @Email(message = "incorrect format!!!")
    @Column(name = "c_email", nullable = false, unique = true, length = 60)
    private String email;
}
