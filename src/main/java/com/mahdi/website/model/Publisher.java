package com.mahdi.website.model;

import jakarta.persistence.*;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

}
