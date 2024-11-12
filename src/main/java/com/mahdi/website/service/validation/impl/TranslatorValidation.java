package com.mahdi.website.service.validation.impl;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.exception.translator.DuplicateTranslatorNationalCodeException;
import com.mahdi.website.exception.translator.DuplicateTranslatorEmailException;
import com.mahdi.website.exception.translator.DuplicateTranslatorPhoneNumberException;
import com.mahdi.website.model.Translator;
import com.mahdi.website.repository.TranslatorRepository;
import com.mahdi.website.service.validation.interfaces.TranslatorValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslatorValidation implements TranslatorValidationInterface {

    private final TranslatorRepository translatorRepository;

    public void translatorEmailValidation(String email) {
        Optional<Translator> translator = translatorRepository.findTranslatorByEmail(email);
        if (translator.isPresent()) {
            throw new DuplicateTranslatorEmailException();
        }
    }

    public void translatorPhoneNumberValidation(String phoneNumber) {
        Optional<Translator> translator = translatorRepository.findTranslatorByPhoneNumber(phoneNumber);
        if (translator.isPresent()) {
            throw new DuplicateTranslatorPhoneNumberException();
        }
    }

    public void translatorNationalCodeValidation(String nationalCode) {
        Optional<Translator> translator = translatorRepository.findTranslatorByNationalCode(nationalCode);
        if (translator.isPresent()) {
            throw new DuplicateTranslatorNationalCodeException();
        }
    }

    @Override
    public void addTranslatorValidation(TranslatorDTO translatorDTO) {
        translatorEmailValidation(translatorDTO.getEmail());
        translatorPhoneNumberValidation(translatorDTO.getPhoneNumber());
        translatorNationalCodeValidation(translatorDTO.getNationalCode());
    }

    @Override
    public void updateTranslatorValidation(Translator translator, TranslatorDTO translatorDTO) {
        if (!translatorDTO.getEmail().equals(translator.getEmail())) {
            translatorEmailValidation(translatorDTO.getEmail());
        }
        if (!translatorDTO.getPhoneNumber().equals(translator.getPhoneNumber())) {
            translatorPhoneNumberValidation(translatorDTO.getPhoneNumber());
        }
        if (!translatorDTO.getNationalCode().equals(translator.getNationalCode())) {
            translatorNationalCodeValidation(translatorDTO.getNationalCode());
        }
    }

}
