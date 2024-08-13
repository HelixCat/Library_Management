package com.mahdi.website.repository;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.model.Translator;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TranslatorRepository extends CrudRepository<Translator, Long>, JpaSpecificationExecutor<Translator> {

    @Query("SELECT translator FROM Translator translator WHERE translator.active = :#{#translatorDTO.active} AND" +
            "(:#{#translatorDTO.name} IS NULL OR translator.name = :#{#translatorDTO.name}) AND " +
            "(:#{#translatorDTO.phoneNumber} IS NULL OR translator.phoneNumber = :#{#translatorDTO.phoneNumber}) AND " +
            "(:#{#translatorDTO.email} IS NULL OR translator.email = :#{#translatorDTO.email})")
    List<Translator> searchTranslator(@Param("translatorDTO") TranslatorDTO translatorDTO);

    @Query("SELECT translator FROM Translator translator WHERE translator.email = :email")
    Optional<Translator> findTranslatorByEmail(@Param("email") String email);

    @Query("SELECT translator FROM Translator translator WHERE translator.phoneNumber = :phoneNumber")
    Optional<Translator> findTranslatorByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT translator FROM Translator translator WHERE translator.name = :name")
    Optional<Translator> findByTranslatorName(@Param("name") String name);
}
