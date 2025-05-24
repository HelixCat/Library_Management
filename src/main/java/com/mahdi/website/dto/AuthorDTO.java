package com.mahdi.website.dto;

import lombok.*;
import com.mahdi.website.model.Book;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private Long id;
    private String email;
    private Boolean active;
    private String lastName;
    private Integer version;
    private String manualId;
    private String firstName;
    private String phoneNumber;
    private Set<Book> books;
}
