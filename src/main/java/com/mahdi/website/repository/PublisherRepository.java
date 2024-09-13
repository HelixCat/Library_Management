package com.mahdi.website.repository;

import com.mahdi.website.model.Publisher;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PublisherRepository extends CrudRepository<Publisher, Long>, JpaSpecificationExecutor<Publisher> {

    @Query("SELECT p FROM Publisher p WHERE p.email = :email")
    Optional<Publisher> findPublisherByEmail(@Param("email") String email);

    @Query("SELECT p FROM Publisher p WHERE p.phoneNumber = :phoneNumber")
    Optional<Publisher> findPublisherByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT p FROM Publisher p WHERE p.name = :name")
    Optional<Publisher> findByPublisherName(@Param("name") String name);

}
