package com.mahdi.website.service.impl;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.mapper.AddressMapper;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.repository.PublisherRepository;
import com.mahdi.website.repository.PublisherSearchSpecification;
import com.mahdi.website.service.interfaces.AddressServiceInterface;
import com.mahdi.website.service.interfaces.PublisherServiceInterface;
import com.mahdi.website.service.validation.interfaces.PublisherValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mahdi.website.exception.publisher.PublisherNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService implements PublisherServiceInterface {

    private final AddressMapper addressMapper;
    private final PublisherMapper publisherMapper;
    private final AddressServiceInterface addressService;
    private final PublisherRepository publisherRepository;
    private final PublisherValidationInterface publisherValidation;

    @Override
    public List<Publisher> searchPublisher(PublisherDTO publisherDTO) {
        PublisherSearchSpecification specification = new PublisherSearchSpecification(publisherDTO);
        return publisherRepository.findAll(specification);
    }

    @Override
    public Publisher savePublisher(PublisherDTO publisherDTO) {
        publisherValidation.addPublisherValidation(publisherDTO);
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        publisher.setActive(Boolean.TRUE);
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher findPublisherByName(String name) {
        return publisherRepository.findByPublisherName(name)
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    public Publisher findPublisherByEmail(String email) {
        return publisherRepository.findPublisherByEmail(email)
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    public Publisher findPublisherByPhoneNumber(String phoneNumber) {
        return publisherRepository.findPublisherByPhoneNumber(phoneNumber)
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    public PublisherDTO findPublisherDTOById(Long id) {
        Publisher publisher = findPublisherById(id);
        PublisherDTO publisherDTO = publisherMapper.toDTO(publisher);
        publisherDTO.setAddresses(addressMapper.toDTOList(publisher.getAddresses()));
        return publisherDTO;
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    public Publisher deactivatePublisherById(Long id) {
        Publisher publisher = findPublisherById(id);
        publisher.setActive(Boolean.FALSE);
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher updatePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = findPublisherById(publisherDTO.getId());
        publisherValidation.updatePublisherValidation(publisher, publisherDTO);
        publisher.setName(publisherDTO.getName());
        publisher.setEmail(publisherDTO.getEmail());
        publisher.setPhoneNumber(publisherDTO.getPhoneNumber());
        publisher.setActive(publisherDTO.getActive());
        return publisherRepository.save(publisher);
    }
}
