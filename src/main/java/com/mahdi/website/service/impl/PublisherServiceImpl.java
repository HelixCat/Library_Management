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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherMapper publisherMapper;
    private final PublisherRepository publisherRepository;
    private final PublisherValidationInterface publisherValidation;

    @Override
    public List<Publisher> searchPublisher(PublisherDTO publisherDTO) {
        PublisherSearchSpecification specification = new PublisherSearchSpecification(publisherDTO);
        return publisherRepository.findAll(specification);
    }

    @Override
    @Cacheable(value = "publisherSearch", key = "T(java.util.Objects).hash(#publisherDTO.name, #publisherDTO.email, #publisherDTO.phoneNumber)", unless = "#result == null or #result.isEmpty()")
    public List<PublisherDTO> searchPublisherDTO(PublisherDTO publisherDTO) {
        return publisherMapper.toDTOList(searchPublisher(publisherDTO));
    }

    @Override
    public Publisher savePublisher(PublisherDTO publisherDTO) {
        publisherValidation.addPublisherValidation(publisherDTO);
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        publisher.setActive(Boolean.TRUE);
        return publisherRepository.save(publisher);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "publishers", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "publisherSearch", allEntries = true)
    })
    public PublisherDTO savePublisherDTO(PublisherDTO publisherDTO) {
        return publisherMapper.toDTO(savePublisher(publisherDTO));
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
    public Publisher deactivatePublisherById(PublisherDTO publisherDTO) {
        Publisher publisher = findPublisherById(publisherDTO.getId());
        publisher.setActive(Boolean.FALSE);
        return publisherRepository.save(publisher);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "publishers", key = "#result.id"),
            @CachePut(value = "publisherDetails", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "publisherSearch", allEntries = true)
    })
    public PublisherDTO deactivatePublisherDTOById(PublisherDTO publisherDTO) {
        return publisherMapper.toDTO(deactivatePublisherById(publisherDTO));
    }

    @Override
    public Publisher updatePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = findPublisherById(publisherDTO.getId());
        publisherValidation.updatePublisherValidation(publisher, publisherDTO);
        Optional.ofNullable(publisherDTO.getName()).ifPresent(publisher::setName);
        Optional.ofNullable(publisherDTO.getEmail()).ifPresent(publisher::setEmail);
        Optional.ofNullable(publisherDTO.getPhoneNumber()).ifPresent(publisher::setPhoneNumber);
        Optional.ofNullable(publisherDTO.getActive()).ifPresent(publisher::setActive);
        return publisherRepository.save(publisher);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "publishers", key = "#result.id"),
            @CachePut(value = "publisherDetails", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "publisherSearch", allEntries = true)
    })
    public PublisherDTO updatePublisherDTO(PublisherDTO publisherDTO) {
        return publisherMapper.toDTO(updatePublisher(publisherDTO));
    }
}
