package com.mahdi.website.mapper;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.model.Translator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TranslatorMapper extends BaseMapper<Translator, TranslatorDTO> {
}
