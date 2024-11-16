package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher>  searchPublisher(PublisherDTO publisherDTO);

    Publisher savePublisher(PublisherDTO publisherDTO);

    Publisher findPublisherByName(String name);

    Publisher findPublisherByEmail(String email);

    Publisher findPublisherByPhoneNumber(String phoneNumber);

    PublisherDTO findPublisherDTOById(Long id);

    Publisher findPublisherById(Long id);

    Publisher deactivatePublisherById(Long id);

    Publisher updatePublisher(PublisherDTO publisherDTO);
}
