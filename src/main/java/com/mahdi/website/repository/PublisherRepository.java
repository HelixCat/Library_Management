package com.mahdi.website.repository;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.model.Publisher;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends CrudRepository<Publisher, Long>, JpaSpecificationExecutor<Publisher> {

    @Query("SELECT publisher FROM Publisher publisher WHERE publisher.active = :#{#publisherDTO.active} AND" +
            "(:#{#publisherDTO.name} IS NULL OR publisher.name = :#{#publisherDTO.name}) AND " +
            "(:#{#publisherDTO.phoneNumber} IS NULL OR publisher.phoneNumber = :#{#publisherDTO.phoneNumber}) AND " +
            "(:#{#publisherDTO.email} IS NULL OR publisher.email = :#{#publisherDTO.email})")
    List<Publisher> searchPublisher(@Param("publisherDTO") PublisherDTO publisherDTO);

    @Query("SELECT p FROM Publisher p WHERE p.email = :email")
    Optional<Publisher> findPublisherByEmail(@Param("email") String email);

    @Query("SELECT p FROM Publisher p WHERE p.phoneNumber = :phoneNumber")
    Optional<Publisher> findPublisherByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT p FROM Publisher p WHERE p.name = :name")
    Optional<Publisher> findByPublisherName(@Param("name") String name);

}
