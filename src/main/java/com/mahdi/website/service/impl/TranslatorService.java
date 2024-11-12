package com.mahdi.website.service.impl;

import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.exception.translator.TranslatorNotFoundException;
import com.mahdi.website.mapper.TranslatorMapper;
import com.mahdi.website.model.Translator;
import com.mahdi.website.repository.TranslatorRepository;
import com.mahdi.website.repository.TranslatorSearchSpecification;
import com.mahdi.website.service.interfaces.TranslatorServiceInterface;
import com.mahdi.website.service.validation.interfaces.TranslatorValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslatorService implements TranslatorServiceInterface {

    private final TranslatorMapper translatorMapper;
    private final TranslatorRepository translatorRepository;
    private final TranslatorValidationInterface translatorValidation;


    @Override
    public List<TranslatorDTO> searchTranslator(TranslatorDTO translatorDTO) {
        TranslatorSearchSpecification  specification = new TranslatorSearchSpecification(translatorDTO);
        List<Translator> translatorList = translatorRepository.findAll(specification);
        return prepareTranslatorList(translatorList);
    }

    @Override
    public Translator saveTranslator(TranslatorDTO translatorDTO) {
        translatorValidation.addTranslatorValidation(translatorDTO);
        Translator translator = prepareTranslator(translatorDTO);
        return translatorRepository.save(translator);
    }

    @Override
    public Translator findTranslatorByFirstName(String firstName) {
        return translatorRepository.findByTranslatorByFirstName(firstName)
                .orElseThrow(() -> new TranslatorNotFoundException("translator with name " + firstName + " does not exist"));
    }

    @Override
    public Translator findTranslatorBylastName(String lastName) {
        return translatorRepository.findByTranslatorByLastName(lastName)
                .orElseThrow(() -> new TranslatorNotFoundException("translator with name " + lastName + " does not exist"));
    }

    @Override
    public Translator findTranslatorByEmail(String email) {
        return translatorRepository.findTranslatorByEmail(email)
                .orElseThrow(() -> new com.mahdi.website.exception.translator.TranslatorNotFoundException("translator with email " + email + " does not exist"));
    }

    @Override
    public Translator findTranslatorByPhoneNumber(String phoneNumber) {
        return translatorRepository.findTranslatorByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new TranslatorNotFoundException("translator with phone number " + phoneNumber + " does not exist"));
    }

    @Override
    public TranslatorDTO findTranslatorDTOById(Long id) {
        Translator translator = findTranslatorById(id);
        return translatorMapper.toDTO(translator);
    }

    @Override
    public Translator findTranslatorById(Long id) {
        return translatorRepository.findById(id).orElseThrow(() -> new TranslatorNotFoundException("translator with id " + id + " does not exist"));
    }

    @Override
    public void deactivateTranslatorById(Long id) {
        Translator translator = findTranslatorById(id);
        translator.setActive(Boolean.FALSE);
        translatorRepository.save(translator);
    }

    @Override
    public void updateTranslator(Long id, TranslatorDTO translatorDTO) {
        Translator translator = findTranslatorById(id);
        translatorValidation.updateTranslatorValidation(translator, translatorDTO);
        translator.setFirstName(translatorDTO.getFirstName());
        translator.setLastName(translatorDTO.getLastName());
        translator.setEmail(translatorDTO.getEmail());
        translator.setPhoneNumber(translatorDTO.getPhoneNumber());
        translator.setActive(translatorDTO.getActive());
        translatorRepository.save(translator);
    }


    private Translator prepareTranslator(TranslatorDTO translatorDTO) {
        Translator translator = translatorMapper.toEntity(translatorDTO);
        translator.setActive(Boolean.TRUE);
        return translator;
    }

    private List<TranslatorDTO> prepareTranslatorList(List<Translator> translatorList) {
        List<TranslatorDTO> translatorDTOList = new ArrayList<>();
        for (Translator translator : translatorList) {
            TranslatorDTO translatorDTO = translatorMapper.toDTO(translator);
            translatorDTOList.add(translatorDTO);
        }
        return translatorDTOList;
    }
}
