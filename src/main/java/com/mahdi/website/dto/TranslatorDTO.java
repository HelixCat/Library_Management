package com.mahdi.website.dto;

import com.mahdi.website.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorDTO {

    private Long id;
    private String email;
    private Boolean active;
    private Integer version;
    private String manualId;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private Set<Book> books;

}
