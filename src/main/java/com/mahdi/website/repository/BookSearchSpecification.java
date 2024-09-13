package com.mahdi.website.repository;

import com.mahdi.website.dto.BookDTO;
import com.mahdi.website.dto.SearchBookDTO;
import com.mahdi.website.model.Author;
import com.mahdi.website.model.Book;
import com.mahdi.website.model.Translator;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookSearchSpecification implements Specification<Book> {

    private SearchBookDTO searchBookDTO;

    public BookSearchSpecification(SearchBookDTO searchBookDTO) {
        this.searchBookDTO = searchBookDTO;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(searchBookDTO.getTitle())) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchBookDTO.getTitle() + "%"));
        }
        if (StringUtils.hasText(searchBookDTO.getBookId())) {
            predicates.add(criteriaBuilder.equal(root.get("bookId"), searchBookDTO.getBookId()));
        }
        if (StringUtils.hasText(searchBookDTO.getPhoneNumber())) {
            predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), searchBookDTO.getPhoneNumber()));
        }
        if (Objects.nonNull(searchBookDTO.getActive())) {
            predicates.add(criteriaBuilder.equal(root.get("active"), searchBookDTO.getActive()));
        }
        if (StringUtils.hasText(searchBookDTO.)) {
            Join<Book, Author> authorJoin = root.join("authors");
            predicates.add(criteriaBuilder.like(authorJoin.get("firstName"), "%" + bookDTO.getAuthor().getFirstName() + "%"));
        }
        if (StringUtils.hasText(bookDTO.getAuthor().getLastName())) {
            Join<Book, Author> authorJoin = root.join("authors");
            predicates.add(criteriaBuilder.like(authorJoin.get("lastName"), "%" + bookDTO.getAuthor().getLastName() + "%"));
        }
        if (StringUtils.hasText(bookDTO.getTranslator().getFirstName())) {
            Join<Book, Translator> translatorJoin = root.join("translators");
            predicates.add(criteriaBuilder.like(translatorJoin.get("firstName"), "%" + bookDTO.getTranslator().getFirstName() + "%"));
        }
        if (StringUtils.hasText(bookDTO.getTranslator().getLastName())) {
            Join<Book, Author> translatorJoin = root.join("translators");
            predicates.add(criteriaBuilder.like(translatorJoin.get("firstName"), "%" + bookDTO.getTranslator().getFirstName() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
