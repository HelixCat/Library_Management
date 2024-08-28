package com.mahdi.website.service.validation;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.model.Translator;

public interface TranslatorValidationInterface {
    void addTranslatorValidation(TranslatorDTO translatorDTO);

    void updateTranslatorValidation(Translator translator, TranslatorDTO translatorDTO);
}
