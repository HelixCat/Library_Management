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

    private String name;
    private String phoneNumber;
    private String email;
    private List<Book> books;
    private List<Address> addresses;
}
