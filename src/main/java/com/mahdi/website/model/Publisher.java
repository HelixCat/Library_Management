package com.mahdi.website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "t_publisher")
@EqualsAndHashCode(callSuper = true)
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
    // In Publisher.java, add:
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();
}
