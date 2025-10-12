package com.mahdi.website.service.validation.impl;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.exception.publisher.DuplicatePublisherEmailException;
import com.mahdi.website.exception.publisher.DuplicatePublisherNameException;
import com.mahdi.website.exception.publisher.DuplicatePublisherPhoneNumberException;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.repository.PublisherRepository;
import com.mahdi.website.service.validation.interfaces.PublisherValidationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PublisherValidation implements PublisherValidationInterface {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherValidation(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void publisherNameValidation(String publisherName) {
        Optional<Publisher> publisher = publisherRepository.findPublisherByName(publisherName);
        if (publisher.isPresent()) {
            throw new DuplicatePublisherNameException();
        }
    }

    public void publisherEmailValidation(String email) {
        Optional<Publisher> publisher = publisherRepository.findPublisherByEmail(email);
        if (publisher.isPresent()) {
            throw new DuplicatePublisherEmailException();
        }
    }

    public void publisherPhoneNumberValidation(String phoneNumber) {
        Optional<Publisher> publisher = publisherRepository.findPublisherByPhoneNumber(phoneNumber);
        if (publisher.isPresent()) {
            throw new DuplicatePublisherPhoneNumberException();
        }
    }

    @Override
    public void addPublisherValidation(PublisherDTO publisherDTO) {
        publisherNameValidation(publisherDTO.getName());
        publisherEmailValidation(publisherDTO.getEmail());
        publisherPhoneNumberValidation(publisherDTO.getPhoneNumber());
    }

    @Override
    public void updatePublisherValidation(Publisher publisher, PublisherDTO publisherDTO) {
        if (Objects.nonNull(publisherDTO.getName()) && !publisherDTO.getName().equals(publisher.getName())) {
            publisherNameValidation(publisherDTO.getName());
        }
        if (Objects.nonNull(publisherDTO.getEmail()) && !publisherDTO.getEmail().equals(publisher.getEmail())) {
            publisherEmailValidation(publisherDTO.getEmail());
        }
        if (Objects.nonNull(publisherDTO.getPhoneNumber()) && !publisherDTO.getPhoneNumber().equals(publisher.getPhoneNumber())) {
            publisherPhoneNumberValidation(publisherDTO.getPhoneNumber());
        }
    }
}
