package com.mahdi.website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "t_author")
public class Author extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(name = "c_first_name", length = 45)
    private String firstName;
    @NotNull
    @NotEmpty
    @Column(name = "c_last_name", length = 45)
    private String lastName;
    @NotNull
    @NotEmpty
    @Email(message = "incorrect format!!!")
    @Column(name = "c_email", unique = true, length = 60)
    private String email;
    @Column(name = "c_national_code", nullable = false, unique = true, length = 10)
    private String nationalCode;
    @NotNull
    @NotEmpty
    @Column(name = "c_phone_number", nullable = false, unique = true, length = 11)
    private String phoneNumber;
    @Lob
    @Column(name = "c_profile_image", length = 200000)
    private byte[] profileImage;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

}
