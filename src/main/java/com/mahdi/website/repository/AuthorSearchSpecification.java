package com.mahdi.website.repository;

import com.mahdi.website.dto.AuthorDTO;
import com.mahdi.website.model.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthorSearchSpecification implements Specification<Author> {

    private AuthorDTO authorDTO;

    public AuthorSearchSpecification(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(authorDTO.getFirstName())) {
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + authorDTO.getFirstName() + "%"));
        }
        if (StringUtils.hasText(authorDTO.getLastName())) {
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + authorDTO.getLastName() + "%"));
        }
        if (StringUtils.hasText(authorDTO.getEmail())) {
            predicates.add(criteriaBuilder.equal(root.get("email"), authorDTO.getEmail()));
        }
        if (StringUtils.hasText(authorDTO.getPhoneNumber())) {
            predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), authorDTO.getPhoneNumber()));
        }
        if (Objects.nonNull(authorDTO.getActive())) {
            predicates.add(criteriaBuilder.equal(root.get("active"), authorDTO.getActive()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
