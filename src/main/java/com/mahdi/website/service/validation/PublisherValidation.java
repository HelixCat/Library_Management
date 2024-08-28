package com.mahdi.website.service.validation;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.exeception.DuplicatePublisherEmailException;
import com.mahdi.website.exeception.DuplicatePublisherNameException;
import com.mahdi.website.exeception.DuplicatePublisherPhoneNumberException;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherValidation implements PublisherValidationInterface {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherValidation(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void publisherNameValidation(String publisherName) {
        Optional<Publisher> publisher = publisherRepository.findByPublisherName(publisherName);
        if (publisher.isPresent()) {
            throw new DuplicatePublisherNameException("this name is taken by other publishers");
        }
    }

    public void publisherEamilValidation(String email) {
        Optional<Publisher> publisher = publisherRepository.findPublisherByEmail(email);
        if (publisher.isPresent()) {
            throw new DuplicatePublisherEmailException("this email is taken by other publishers");
        }
    }

    public void publisherPhoneNumberValidation(String phoneNumber) {
        Optional<Publisher> publisher = publisherRepository.findPublisherByPhoneNumber(phoneNumber);
        if (publisher.isPresent()) {
            throw new DuplicatePublisherPhoneNumberException("this phone number is taken by other publishers");
        }
    }

    @Override
    public void addPublisherValidation(PublisherDTO publisherDTO) {
        publisherNameValidation(publisherDTO.getName());
        publisherEamilValidation(publisherDTO.getEmail());
        publisherPhoneNumberValidation(publisherDTO.getPhoneNumber());
    }

    @Override
    public void updatePublisherValidation(Publisher publisher, PublisherDTO publisherDTO) {
        if (!publisherDTO.getName().equals(publisher.getName())) {
            publisherNameValidation(publisherDTO.getName());
        }
        if (!publisherDTO.getEmail().equals(publisher.getEmail())) {
            publisherEamilValidation(publisherDTO.getEmail());
        }
        if (!publisherDTO.getPhoneNumber().equals(publisher.getPhoneNumber())) {
            publisherPhoneNumberValidation(publisherDTO.getPhoneNumber());
        }
    }
}
