package com.mahdi.website.service.validation;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.exeception.translatorExceptions.DuplicateTranslatorEmailException;
import com.mahdi.website.exeception.translatorExceptions.DuplicateTranslatorNameException;
import com.mahdi.website.exeception.translatorExceptions.DuplicateTranslatorPhoneNumberException;
import com.mahdi.website.model.Translator;
import com.mahdi.website.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TranslatorValidation implements TranslatorValidationInterface {

    private final TranslatorRepository translatorRepository;

    @Autowired
    public TranslatorValidation(TranslatorRepository translatorRepository) {
        this.translatorRepository = translatorRepository;
    }

    public void translatorNameValidation(String translatorName) {
        Optional<Translator> translator = translatorRepository.findByTranslatorName(translatorName);
        if (translator.isPresent()) {
            throw new DuplicateTranslatorNameException("this name is taken by other translators");
        }
    }

    public void translatorEamilValidation(String email) {
        Optional<Translator> translator = translatorRepository.findTranslatorByEmail(email);
        if (translator.isPresent()) {
            throw new DuplicateTranslatorEmailException("this email is taken by other translators");
        }
    }

    public void translatorPhoneNumberValidation(String phoneNumber) {
        Optional<Translator> translator = translatorRepository.findTranslatorByPhoneNumber(phoneNumber);
        if (translator.isPresent()) {
            throw new DuplicateTranslatorPhoneNumberException("this phone number is taken by other translators");
        }
    }

    @Override
    public void addTranslatorValidation(TranslatorDTO translatorDTO) {
        translatorNameValidation(translatorDTO.getName());
        translatorEamilValidation(translatorDTO.getEmail());
        translatorPhoneNumberValidation(translatorDTO.getPhoneNumber());
    }

    @Override
    public void updateTranslatorValidation(Translator translator, TranslatorDTO translatorDTO) {
        if (!translatorDTO.getName().equals(translator.getName())) {
            translatorNameValidation(translatorDTO.getName());
        }
        if (!translatorDTO.getEmail().equals(translator.getEmail())) {
            translatorEamilValidation(translatorDTO.getEmail());
        }
        if (!translatorDTO.getPhoneNumber().equals(translator.getPhoneNumber())) {
            translatorPhoneNumberValidation(translatorDTO.getPhoneNumber());
        }
    }

}
