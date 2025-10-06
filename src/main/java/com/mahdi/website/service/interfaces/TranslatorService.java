package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.model.Translator;

import java.util.List;

public interface TranslatorService {
    List<Translator> searchTranslator(TranslatorDTO translatorDTO);

    Translator saveTranslator(TranslatorDTO translatorDTO);

    Translator findTranslatorByFirstName(String firstName);

    Translator findTranslatorBylastName(String lastName);

    Translator findTranslatorByEmail(String email);

    Translator findTranslatorByPhoneNumber(String phoneNumber);

    TranslatorDTO findTranslatorDTOById(Long id);

    Translator findTranslatorById(Long id);

    Translator deactivateTranslatorById(Long id);

    Translator updateTranslator(TranslatorDTO translatorDTO);
}
