package com.mahdi.website.service.validation.interfaces;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.model.Publisher;

public interface PublisherValidationInterface {

    void addPublisherValidation(PublisherDTO publisherDTO);

    void updatePublisherValidation(Publisher publisher, PublisherDTO publisherDTO);
}
