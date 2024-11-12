package com.mahdi.website.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private String bookId;
    private Boolean active;
    private Integer version;
    private String manualId;
    private Date publishDate;
    private String publishYear;
    private PublisherDTO publisher;
    private Integer publisherNumber;
    private Set<AuthorDTO> authors;
    private Set<TranslatorDTO> translators;
}
