package com.mahdi.website.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchBookDTO {
    private Long id;
    private String title;
    private String bookId;
    private Boolean active;
    private Integer version;
    private String manualId;
    private LocalDate publishDate;
    private String authorName;
    private LocalDate publishYear;
    private String publisherName;
    private String translatorName;
}
