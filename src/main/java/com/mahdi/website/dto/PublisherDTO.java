package com.mahdi.website.dto;

import com.mahdi.website.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {

    private Long id;
    private Boolean active;
    private Integer version;
    private String manualId;
    private String name;
    private String phoneNumber;
    private String email;
    private List<Book> books;
    private AddressDTO addressDTO;
}
