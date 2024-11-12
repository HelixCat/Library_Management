package com.mahdi.website.dto;

import com.mahdi.website.model.Address;
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
    private String name;
    private String email;
    private Boolean active;
    private String manualId;
    private Integer version;
    private List<Book> books;
    private String phoneNumber;
    private List<AddressDTO> addresses;
}
