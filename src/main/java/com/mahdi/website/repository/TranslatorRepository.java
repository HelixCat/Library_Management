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
            "(:#{#translatorDTO.firstName} IS NULL OR translator.firstName = :#{#translatorDTO.firstName}) AND " +
            "(:#{#translatorDTO.lastName} IS NULL OR translator.lastName = :#{#translatorDTO.lastName}) AND " +
            "(:#{#translatorDTO.phoneNumber} IS NULL OR translator.phoneNumber = :#{#translatorDTO.phoneNumber}) AND " +
            "(:#{#translatorDTO.nationalCode} IS NULL OR translator.nationalCode = :#{#translatorDTO.nationalCode}) AND " +
            "(:#{#translatorDTO.email} IS NULL OR translator.email = :#{#translatorDTO.email})")
    List<Translator> searchTranslator(@Param("translatorDTO") TranslatorDTO translatorDTO);

    @Query("SELECT translator FROM Translator translator WHERE translator.email = :email")
    Optional<Translator> findTranslatorByEmail(@Param("email") String email);

    @Query("SELECT translator FROM Translator translator WHERE translator.phoneNumber = :phoneNumber")
    Optional<Translator> findTranslatorByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT translator FROM Translator translator WHERE translator.firstName = :firstName")
    Optional<Translator> findByTranslatorByfirstName(@Param("firstName") String firstName);

    @Query("SELECT translator FROM Translator translator WHERE translator.lastName = :lastName")
    Optional<Translator> findByTranslatorBylastName(@Param("lastName") String lastName);

    @Query("SELECT translator FROM Translator translator WHERE translator.nationalCode = :nationalCode")
    Optional<Translator> findTranslatorByNationalCode(@Param("nationalCode") String nationalCode);
}
