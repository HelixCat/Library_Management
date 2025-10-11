package com.mahdi.website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_book", indexes = {
        @Index(name = "idx_book_title", columnList = "c_book_title"),
        @Index(name = "idx_publish_year", columnList = "c_publish_year")
})
@ToString(exclude = {"translators", "authors"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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
    private LocalDate publishDate;
    @Column(name = "c_publish_year", nullable = false)
    private LocalDate  publishYear;
    @Lob
    @Column(name = "c_profile_image", length = 200000, columnDefinition = "LONGBLOB")
    private byte[] profileImage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_translator",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private Set<Translator> translators;
}
