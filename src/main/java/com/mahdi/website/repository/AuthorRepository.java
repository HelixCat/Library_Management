package com.mahdi.website.repository;

import com.mahdi.website.model.Author;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    @Query("SELECT author FROM Author author WHERE author.email = :email")
    Optional<Author> findAuthorByEmail(@Param("email") String email);

    @Query("SELECT author FROM Author author WHERE author.phoneNumber = :phoneNumber")
    Optional<Author> findAuthorByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT author FROM Author author WHERE author.firstName = :firstName")
    Optional<Author> findByAuthorByFirstName(@Param("firstName") String firstName);

    @Query("SELECT author FROM Author author WHERE author.lastName = :lastName")
    Optional<Author> findByAuthorByLastName(@Param("lastName") String lastName);
}
