package com.mahdi.website.repository;

import com.mahdi.website.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    Optional<Author> findAuthorsByEmail(@Param("email") String email);

    Optional<Author> findAuthorsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    Optional<Author> findAuthorsByFirstName(@Param("firstName") String firstName);

    Optional<Author> findAuthorsByLastName(@Param("lastName") String lastName);
}
