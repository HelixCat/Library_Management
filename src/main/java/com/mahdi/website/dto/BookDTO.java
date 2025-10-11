package com.mahdi.website.dto;

import lombok.*;

import java.time.LocalDate;
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
    private LocalDate  publishDate;
    private LocalDate publishYear;
    private PublisherDTO publisher;
    private Integer publisherNumber;
    private Set<AuthorDTO> authors;
    private Set<TranslatorDTO> translators;
}
