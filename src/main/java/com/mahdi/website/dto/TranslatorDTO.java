package com.mahdi.website.dto;

import com.mahdi.website.model.Address;
import com.mahdi.website.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorDTO {

    private Long id;
    private Boolean active;
    private Integer version;
    private String manualId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nationalCode;
    private Set<Book> books;

}
