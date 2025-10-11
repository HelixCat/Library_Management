package com.mahdi.website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_translator")
@ToString(exclude = {"books"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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
    @JsonIgnore
    @ManyToMany(mappedBy = "translators")
    private Set<Book> books;
}
