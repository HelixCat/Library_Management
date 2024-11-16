package com.mahdi.website.repository;

import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserSearchSpecification implements Specification<User> {

    private UserDTO userDTO;

    public UserSearchSpecification(UserDTO UserDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(userDTO.getFirstName())) {
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + userDTO.getFirstName() + "%"));
        }
        if (StringUtils.hasText(userDTO.getLastName())) {
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + userDTO.getLastName() + "%"));
        }
        if (StringUtils.hasText(userDTO.getUsername())) {
            predicates.add(criteriaBuilder.like(root.get("userName"), "%" + userDTO.getUsername() + "%"));
        }
        if (StringUtils.hasText(userDTO.getEmail())) {
            predicates.add(criteriaBuilder.equal(root.get("email"), userDTO.getEmail()));
        }
        if (StringUtils.hasText(userDTO.getNationalCode())) {
            predicates.add(criteriaBuilder.equal(root.get("nationalCode"), userDTO.getNationalCode()));
        }
        if (StringUtils.hasText(userDTO.getPhoneNumber())) {
            predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), userDTO.getPhoneNumber()));
        }
        if (Objects.nonNull(userDTO.getActive())) {
            predicates.add(criteriaBuilder.equal(root.get("active"), userDTO.getActive()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
