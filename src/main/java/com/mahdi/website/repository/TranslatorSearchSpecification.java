package com.mahdi.website.repository;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.model.Translator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TranslatorSearchSpecification implements Specification<Translator> {

    private TranslatorDTO translatorDTO;

    public TranslatorSearchSpecification(TranslatorDTO translatorDTO) {
        this.translatorDTO = translatorDTO;
    }

    @Override
    public Predicate toPredicate(Root<Translator> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(translatorDTO.getFirstName())) {
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + translatorDTO.getFirstName() + "%"));
        }
        if (StringUtils.hasText(translatorDTO.getLastName())) {
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + translatorDTO.getLastName() + "%"));
        }
        if (StringUtils.hasText(translatorDTO.getEmail())) {
            predicates.add(criteriaBuilder.equal(root.get("email"), translatorDTO.getEmail()));
        }
        if (StringUtils.hasText(translatorDTO.getPhoneNumber())) {
            predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), translatorDTO.getPhoneNumber()));
        }
        if (Objects.nonNull(translatorDTO.getActive())) {
            predicates.add(criteriaBuilder.equal(root.get("active"), translatorDTO.getActive()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
