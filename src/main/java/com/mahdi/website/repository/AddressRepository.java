package com.mahdi.website.repository;

import com.mahdi.website.model.Address;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    Optional<Address> findByPostalCode(@Param("postalCode") String postalCode);
}
