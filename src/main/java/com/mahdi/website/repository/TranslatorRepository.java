package com.mahdi.website.repository;

import com.mahdi.website.model.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TranslatorRepository extends JpaRepository<Translator, Long>, JpaSpecificationExecutor<Translator> {

    Optional<Translator> findTranslatorByEmail(@Param("email") String email);

    Optional<Translator> findTranslatorByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    Optional<Translator> findTranslatorByFirstName(@Param("firstName") String firstName);

    Optional<Translator> findTranslatorByLastName(@Param("lastName") String lastName);
}
