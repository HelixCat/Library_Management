package com.mahdi.website.repository;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.model.Publisher;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PublisherSearchSpecification implements Specification<Publisher> {

    private PublisherDTO publisherDTO;

    public PublisherSearchSpecification(PublisherDTO publisherDTO) {
        this.publisherDTO = publisherDTO;
    }

    @Override
    public Predicate toPredicate(Root<Publisher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(publisherDTO.getName())) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + publisherDTO.getName() + "%"));
        }
        if (StringUtils.hasText(publisherDTO.getEmail())) {
            predicates.add(criteriaBuilder.equal(root.get("email"), publisherDTO.getEmail()));
        }
        if (StringUtils.hasText(publisherDTO.getPhoneNumber())) {
            predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), publisherDTO.getPhoneNumber()));
        }
        if (Objects.nonNull(publisherDTO.getActive())) {
            predicates.add(criteriaBuilder.equal(root.get("active"), publisherDTO.getActive()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
