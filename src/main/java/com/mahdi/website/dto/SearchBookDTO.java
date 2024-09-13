package com.mahdi.website.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchBookDTO {
    private Long id;
    private Boolean active;
    private Integer version;
    private String manualId;
    private String title;
    private String bookId;
    private String publishYear;
    private String publisherName;
    private String authorName;
    private String translatorName;
    private Date publishDate;
}
