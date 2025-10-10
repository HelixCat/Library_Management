package com.mahdi.website.service.impl;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.exception.publisher.PublisherNotFoundException;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.repository.PublisherRepository;
import com.mahdi.website.repository.PublisherSearchSpecification;
import com.mahdi.website.service.interfaces.PublisherService;
import com.mahdi.website.service.validation.interfaces.PublisherValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherMapper publisherMapper;
    private final PublisherRepository publisherRepository;
    private final PublisherValidationInterface publisherValidation;

    @Override
    @Cacheable(value = "publisherSearch", key = "T(java.util.Objects).hash(#publisherDTO.publisherName, #publisherDTO.email, #publisherDTO.phoneNumber)", unless = "#result == null or #result.isEmpty()")
    public List<Publisher> searchPublisher(PublisherDTO publisherDTO) {
        PublisherSearchSpecification specification = new PublisherSearchSpecification(publisherDTO);
        return publisherRepository.findAll(specification);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "publishers", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "publisherSearch", allEntries = true)
    })
    public Publisher savePublisher(PublisherDTO publisherDTO) {
        publisherValidation.addPublisherValidation(publisherDTO);
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        publisher.setActive(Boolean.TRUE);
        return publisherRepository.save(publisher);
    }

    @Override
    @Cacheable(value = "publishers", key = "#name", unless = "#result == null")
    public Publisher findPublisherByName(String name) {
        return publisherRepository.findPublisherByName(name)
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    @Cacheable(value = "publishers", key = "#email", unless = "#result == null")
    public Publisher findPublisherByEmail(String email) {
        return publisherRepository.findPublisherByEmail(email)
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    @Cacheable(value = "publishers", key = "#phoneNumber", unless = "#result == null")
    public Publisher findPublisherByPhoneNumber(String phoneNumber) {
        return publisherRepository.findPublisherByPhoneNumber(phoneNumber)
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    @Cacheable(value = "publishers", key = "#id", unless = "#result == null")
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    @Cacheable(value = "publisherDetails", key = "#id", unless = "#result == null")
    public PublisherDTO findPublisherDTOById(Long id) {
        Publisher publisher = findPublisherById(id);
        return publisherMapper.toDTO(publisher);
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
