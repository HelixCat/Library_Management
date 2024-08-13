package com.mahdi.website.service;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.dto.TranslatorDTO;
import com.mahdi.website.exeception.PublisherNotFoundException;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.model.Translator;
import com.mahdi.website.repository.PublisherSearchSpecification;
import com.mahdi.website.repository.TranslatorRepository;
import com.mahdi.website.service.validation.TranslatorValidationInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TranslatorService implements TranslatorServiceInterface{

    private final TranslatorRepository translatorRepository;
    private final ModelMapper modelMapper;
    private final TranslatorValidationInterface translatorValidationInterface;

    @Autowired
    public TranslatorService(TranslatorRepository translatorRepository, ModelMapper modelMapper, TranslatorValidationInterface translatorValidationInterface) {
        this.translatorRepository = translatorRepository;
        this.modelMapper = modelMapper;
        this.translatorValidationInterface = translatorValidationInterface;
    }

    @Override
    public List<Translator> searchTranslator(TranslatorDTO translatorDTO) {
        List<Translator> translatorList = translatorRepository.searchTranslator(translatorDTO);
        return prepareTranslatorList(translatorList);
    }

    @Override
    public Publisher savePublisher(PublisherDTO publisherDTO) {
        publisherValidation.addPublisherValidation(publisherDTO);
        Publisher publisher = preparePublisher(publisherDTO);
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher findPublisherByName(String name) {
        return publisherRepository.findByPublisherName(name)
                .orElseThrow(() -> new PublisherNotFoundException("publisher with name " + name + " does not exist"));
    }

    @Override
    public Publisher findPublisherByEmail(String email) {
        return publisherRepository.findPublisherByEmail(email)
                .orElseThrow(() -> new PublisherNotFoundException("publisher with email " + email + " does not exist"));
    }

    @Override
    public Publisher findPublisherByPhoneNumber(String phoneNumber) {
        return publisherRepository.findPublisherByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new PublisherNotFoundException("publisher with phone number " + phoneNumber + " does not exist"));
    }

    @Override
    public PublisherDTO findPublisherDTOById(Long id) {
        Publisher publisher = findPublisherById(id);
        PublisherDTO publisherDTO = modelMapper.map(publisher, PublisherDTO.class);
        publisherDTO.setAddressDTO(modelMapper.map(publisher.getAddresses().getFirst(), AddressDTO.class));
        return publisherDTO;
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new PublisherNotFoundException("publisher with id " + id + " does not exist"));
    }

    @Override
    public void deactivatePublisherById(Long id) {
        Publisher publisher = findPublisherById(id);
        publisher.setActive(Boolean.FALSE);
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Long id, TranslatorDTO translatorDTO) {
        Translator translator = findTranslatorById(id);
        publisherValidation.updatePublisherValidation(translator, translatorDTO);
        translator.setName(translatorDTO.getName());
        translator.setEmail(translatorDTO.getEmail());
        translator.setPhoneNumber(translatorDTO.getPhoneNumber());
        translator.setActive(translatorDTO.getActive());
        translatorRepository.save(tr);
    }


    private Publisher preparePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = modelMapper.map(publisherDTO, Publisher.class);
        publisher.setActive(Boolean.TRUE);
        return publisher;
    }

    private List<TranslatorDTO> prepareTranslatorList(List<Translator> translatorList) {
        List<TranslatorDTO> translatorDTOList = new ArrayList<>();
        for (Translator translator : translatorList) {
            TranslatorDTO translatorDTO = preparePublisherDTO(translator);
            translatorDTOList.add(translatorDTO);
        }
        return translatorDTOList;
    }

    private TranslatorDTO preparePublisherDTO(Translator translator) {
        return modelMapper.map(translator, TranslatorDTO.class);
    }
}
