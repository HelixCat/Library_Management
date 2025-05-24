package com.mahdi.website.repository;

import com.mahdi.website.model.Translator;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TranslatorRepository extends CrudRepository<Translator, Long>, JpaSpecificationExecutor<Translator> {

    @Query("SELECT translator FROM Translator translator WHERE translator.email = :email")
    Optional<Translator> findTranslatorByEmail(@Param("email") String email);

    @Query("SELECT translator FROM Translator translator WHERE translator.phoneNumber = :phoneNumber")
    Optional<Translator> findTranslatorByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT translator FROM Translator translator WHERE translator.firstName = :firstName")
    Optional<Translator> findByTranslatorByFirstName(@Param("firstName") String firstName);

    @Query("SELECT translator FROM Translator translator WHERE translator.lastName = :lastName")
    Optional<Translator> findByTranslatorByLastName(@Param("lastName") String lastName);
}
