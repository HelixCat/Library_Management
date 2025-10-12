package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> searchPublisher(PublisherDTO publisherDTO);

    List<PublisherDTO> searchPublisherDTO(PublisherDTO publisherDTO);

    Publisher savePublisher(PublisherDTO publisherDTO);

    PublisherDTO savePublisherDTO(PublisherDTO publisherDTO);

    Publisher findPublisherByName(String name);

    Publisher findPublisherByEmail(String email);

    Publisher findPublisherByPhoneNumber(String phoneNumber);

    PublisherDTO findPublisherDTOById(Long id);

    Publisher findPublisherById(Long id);

    Publisher deactivatePublisherById(PublisherDTO publisherDTO);

    PublisherDTO deactivatePublisherDTOById(PublisherDTO publisherDTO);

    Publisher updatePublisher(PublisherDTO publisherDTO);

    PublisherDTO updatePublisherDTO(PublisherDTO publisherDTO);
}
