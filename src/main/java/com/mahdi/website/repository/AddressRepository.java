package com.mahdi.website.repository;

import com.mahdi.website.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
