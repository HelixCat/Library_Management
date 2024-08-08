package com.mahdi.website.dto;

import com.mahdi.website.model.Author;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.model.Translator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private Boolean active;
    private Integer version;
    private String manualId;
    private String title;
    private String bookId;
    private Date publishDate;
    private Integer publisherNumber;
    private String publishYear;
    private Publisher publisher;
    private Set<Author> authors;
    private Set<Translator> translators;
}
