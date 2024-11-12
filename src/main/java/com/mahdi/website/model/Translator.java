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
@Table(name = "t_translator")
public class Translator extends BaseEntity {

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
    @Column(name = "c_email", length = 60)
    private String email;
    @NotNull
    @NotEmpty
    @Column(name = "c_phone_number", nullable = false, unique = true, length = 11)
    private String phoneNumber;
    @Column(name = "c_national_code", nullable = false, unique = true, length = 10)
    private String nationalCode;
    @ManyToMany(mappedBy = "translators")
    private Set<Book> books;
}
