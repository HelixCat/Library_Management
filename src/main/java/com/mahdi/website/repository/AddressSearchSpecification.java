package com.mahdi.website.repository;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.*;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressSearchSpecification implements Specification<Address> {

    private AddressDTO addressDTO;

    public AddressSearchSpecification(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    @Override
    public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(addressDTO.getCountry())) {
            predicates.add(criteriaBuilder.like(root.get("country"), "%" + addressDTO.getCountry() + "%"));
        }
        if (StringUtils.hasText(addressDTO.getProvince())) {
            predicates.add(criteriaBuilder.like(root.get("province"), "%" + addressDTO.getProvince() + "%"));
        }
        if (StringUtils.hasText(addressDTO.getCity())) {
            predicates.add(criteriaBuilder.like(root.get("city"), "%" + addressDTO.getCity() + "%"));
        }
        if (StringUtils.hasText(addressDTO.getPostalCode())) {
            predicates.add(criteriaBuilder.equal(root.get("postalCode"), addressDTO.getPostalCode()));
        }

        PublisherDTO publisherDTO = addressDTO.getPublisher();
        if (Objects.nonNull(publisherDTO)) {
            Join<Publisher, Address> authorJoin = root.join("publisher");
            if (Objects.nonNull(publisherDTO.getId())) {
                predicates.add(criteriaBuilder.equal(authorJoin.get("id"),  publisherDTO.getId()));
            }
            if (StringUtils.hasText(publisherDTO.getEmail())) {
                predicates.add(criteriaBuilder.equal(authorJoin.get("email"), publisherDTO.getEmail()));
            }
        }

        UserDTO userDTO = addressDTO.getUser();
        if (Objects.nonNull(userDTO)) {
            Join<Publisher, Address> authorJoin = root.join("user");
            if (Objects.nonNull(userDTO.getId())) {
                predicates.add(criteriaBuilder.equal(authorJoin.get("id"),  publisherDTO.getId()));
            }
            if (StringUtils.hasText(publisherDTO.getEmail())) {
                predicates.add(criteriaBuilder.equal(authorJoin.get("email"), publisherDTO.getEmail()));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
