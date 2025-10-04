package com.mahdi.website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "t_book")
public class Book extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(name = "c_book_title", nullable = false, length = 50)
    private String title;
    @NotNull
    @NotEmpty
    @Column(name = "c_book_id", nullable = false, unique = true, length = 80)
    private String bookId;
    @NotNull
    @NotEmpty
    @Column(name = "c_publish_date", nullable = false)
    private Date publishDate;
    @Column(name = "c_publish_year", nullable = false)
    private String publishYear;
    @Lob
    @Column(name = "c_profile_image", length = 200000, columnDefinition = "LONGBLOB")
    private byte[] profileImage;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    @ManyToMany
    @JoinTable(
            name = "book_translator",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private Set<Translator> translators;
}
