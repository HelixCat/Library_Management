package com.mahdi.website.service.impl;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.exception.translator.TranslatorNotFoundException;
import com.mahdi.website.mapper.TranslatorMapper;
import com.mahdi.website.model.Translator;
import com.mahdi.website.repository.TranslatorRepository;
import com.mahdi.website.repository.TranslatorSearchSpecification;
import com.mahdi.website.service.interfaces.TranslatorService;
import com.mahdi.website.service.validation.interfaces.TranslatorValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslatorServiceImpl implements TranslatorService {

    private final TranslatorMapper translatorMapper;
    private final TranslatorRepository translatorRepository;
    private final TranslatorValidationInterface translatorValidation;


    @Override
    @Cacheable(value = "translatorSearch", key = "T(java.util.Objects).hash(#translator.firstName, #translator.lastName, #translator.email)", unless = "#result == null or #result.isEmpty()")
    public List<Translator> searchTranslator(TranslatorDTO translatorDTO) {
        TranslatorSearchSpecification specification = new TranslatorSearchSpecification(translatorDTO);
        List<Translator> translatorList = translatorRepository.findAll(specification);
        return translatorList;
    }

    @Override
    @Caching(put = {
            @CachePut(value = "translators", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "translatorSearch", allEntries = true)
    })
    public Translator saveTranslator(TranslatorDTO translatorDTO) {
        translatorValidation.addTranslatorValidation(translatorDTO);
        Translator translator = prepareTranslator(translatorDTO);
        return translatorRepository.save(translator);
    }

    @Override
    @Cacheable(value = "translators", key = "#firstName", unless = "#result == null")
    public Translator findTranslatorByFirstName(String firstName) {
        return translatorRepository.findByTranslatorByFirstName(firstName)
                .orElseThrow(TranslatorNotFoundException::new);
    }

    @Override
    @Cacheable(value = "translators", key = "#lastName", unless = "#result == null")
    public Translator findTranslatorBylastName(String lastName) {
        return translatorRepository.findByTranslatorByLastName(lastName)
                .orElseThrow(TranslatorNotFoundException::new);
    }

    @Override
    @Cacheable(value = "translators", key = "#email", unless = "#result == null")
    public Translator findTranslatorByEmail(String email) {
        return translatorRepository.findTranslatorByEmail(email)
                .orElseThrow(TranslatorNotFoundException::new);
    }

    @Override
    @Cacheable(value = "translators", key = "#phoneNumber", unless = "#result == null")
    public Translator findTranslatorByPhoneNumber(String phoneNumber) {
        return translatorRepository.findTranslatorByPhoneNumber(phoneNumber)
                .orElseThrow(TranslatorNotFoundException::new);
    }

    @Override
    public TranslatorDTO findTranslatorDTOById(Long id) {
        Translator translator = findTranslatorById(id);
        return translatorMapper.toDTO(translator);
    }

    @Override
    public Translator findTranslatorById(Long id) {
        return translatorRepository.findById(id).orElseThrow(TranslatorNotFoundException::new);
    }

    @Override
    public Translator deactivateTranslatorById(Long id) {
        Translator translator = findTranslatorById(id);
        translator.setActive(Boolean.FALSE);
        return translatorRepository.save(translator);
    }

    @Override
    public Translator updateTranslator(TranslatorDTO translatorDTO) {
        Translator translator = findTranslatorById(translatorDTO.getId());
        translatorValidation.updateTranslatorValidation(translator, translatorDTO);
        translator.setFirstName(translatorDTO.getFirstName());
        translator.setLastName(translatorDTO.getLastName());
        translator.setEmail(translatorDTO.getEmail());
        translator.setPhoneNumber(translatorDTO.getPhoneNumber());
        translator.setActive(translatorDTO.getActive());
        return translatorRepository.save(translator);
    }


    private Translator prepareTranslator(TranslatorDTO translatorDTO) {
        Translator translator = translatorMapper.toEntity(translatorDTO);
        translator.setActive(Boolean.TRUE);
        return translator;
    }
}
