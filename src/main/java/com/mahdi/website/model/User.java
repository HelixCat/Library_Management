package com.mahdi.website.model;

import com.mahdi.website.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(name = "c_user_name", nullable = false, unique = true, length = 16)
    private String username;
    @Column(name = "c_first_name", length = 45)
    private String firstName;
    @Column(name = "c_last_name", length = 45)
    private String lastName;
    @Email(message = "incorrect format!!!")
    @Column(name = "c_email", length = 60)
    private String email;
    @NotNull
    @NotEmpty
    @Column(name = "c_phone_number", nullable = false, unique = true, length = 11)
    private String phoneNumber;
    @NotNull
    @NotEmpty
    @Column(name = "c_password", nullable = false, length = 4000)
    private String password;
    @NotNull
    @NotEmpty
    @Column(name = "c_national_code", nullable = false, unique = true, length = 10)
    private String nationalCode;
    @Column(name = "c_gender", nullable = false, length = 10)
    private String gender;
    @Column(name = "c_birthday", length = 10)
    private String birthday;
    @Column(name = "c_register_day", length = 10)
    private String registerDay;
    @Lob
    @Column(name = "c_profile_image", length = 200000, columnDefinition = "LONGBLOB")
    private byte[] profileImage;
    @NotNull
    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "t_user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();
}

