package com.mahdi.website.repository;

import com.mahdi.website.model.Logg;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Logg, String> {
}
