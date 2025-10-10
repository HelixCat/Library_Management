package com.mahdi.website.repository;

import com.mahdi.website.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @EntityGraph(attributePaths = "roles")
    Optional<User> findUserByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findUserByUsername(@Param("username") String username);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findUserByNationalCode(@Param("nationalCode") String nationalCode);
}
