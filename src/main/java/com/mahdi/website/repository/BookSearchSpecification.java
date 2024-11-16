package com.mahdi.website.repository;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.dto.SearchBookDTO;
import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.model.Author;
import com.mahdi.website.model.Book;
import com.mahdi.website.model.Translator;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class BookSearchSpecification implements Specification<Book> {

    private final BookDTO bookDTO;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(bookDTO.getTitle())) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + bookDTO.getTitle() + "%"));
        }
        if (StringUtils.hasText(bookDTO.getBookId())) {
            predicates.add(criteriaBuilder.equal(root.get("bookId"), bookDTO.getBookId()));
        }
        if (Objects.nonNull(bookDTO.getActive())) {
            predicates.add(criteriaBuilder.equal(root.get("active"), bookDTO.getActive()));
        }
        for (AuthorDTO authorDTO : bookDTO.getAuthors()) {
            if (Objects.nonNull(authorDTO)) {
                Join<Book, Author> authorJoin = root.join("authors");
                if (StringUtils.hasText(authorDTO.getFirstName())) {
                    predicates.add(criteriaBuilder.like(authorJoin.get("firstName"), "%" + authorDTO.getFirstName() + "%"));
                }
                if (StringUtils.hasText(authorDTO.getLastName())) {
                    predicates.add(criteriaBuilder.like(authorJoin.get("lastName"), "%" + authorDTO.getLastName() + "%"));
                }
            }
        }

        for (TranslatorDTO translatorDTO : bookDTO.getTranslators()) {
            if (Objects.nonNull(translatorDTO)) {
                Join<Book, Author> authorJoin = root.join("translators");
                if (StringUtils.hasText(translatorDTO.getFirstName())) {
                    predicates.add(criteriaBuilder.like(authorJoin.get("firstName"), "%" + translatorDTO.getFirstName() + "%"));
                }
                if (StringUtils.hasText(translatorDTO.getLastName())) {
                    predicates.add(criteriaBuilder.like(authorJoin.get("lastName"), "%" + translatorDTO.getLastName() + "%"));
                }
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
