package com.mahdi.website.mapper;

import org.mapstruct.Mapper;
import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.model.Translator;


@Mapper(componentModel = "spring")
public interface TranslatorMapper {

    Translator toTranslator(TranslatorDTO translatorDTO);

    TranslatorDTO toTranslatorDTO(Translator translator);

}
