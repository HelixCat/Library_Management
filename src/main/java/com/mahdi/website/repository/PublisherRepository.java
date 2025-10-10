package com.mahdi.website.repository;

import com.mahdi.website.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>, JpaSpecificationExecutor<Publisher> {

    Optional<Publisher> findPublisherByEmail(@Param("email") String email);

    Optional<Publisher> findPublisherByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    Optional<Publisher> findPublisherByName(@Param("name") String name);

}
