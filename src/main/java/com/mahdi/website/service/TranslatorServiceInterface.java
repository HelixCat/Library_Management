package com.mahdi.website.service;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.model.Translator;

import java.util.List;

public interface TranslatorServiceInterface {
    List<TranslatorDTO> searchTranslator(TranslatorDTO translatorDTO);

    Translator saveTranslator(TranslatorDTO translatorDTO);

    Translator findTranslatorByName(String name);

    Translator findTranslatorByEmail(String email);

    Translator findTranslatorByPhoneNumber(String phoneNumber);

    TranslatorDTO findTranslatorDTOById(Long id);

    Translator findTranslatorById(Long id);

    void deactivateTranslatorById(Long id);

    void updateTranslator(Long id, TranslatorDTO translatorDTO);
}
