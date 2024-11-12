package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.PublisherRemote;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.service.impl.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/publisher-management")
public class PublisherResource implements PublisherRemote {

    private final PublisherMapper publisherMapper;
    private final PublisherService publisherService;

    @Override
    public ResponseEntity<List<PublisherDTO>> searchPublishers(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherMapper.toDTOList(publisherService.searchPublisher(publisherDTO)), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<PublisherDTO> savePublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherMapper.toDTO(publisherService.savePublisher(publisherDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PublisherDTO> deactivatePublisher(@RequestBody Long publisherId) {
        return new ResponseEntity<>(publisherMapper.toDTO(publisherService.deactivatePublisherById(publisherId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PublisherDTO> updatePublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherMapper.toDTO(publisherService.updatePublisher(publisherDTO)), HttpStatus.OK);
    }
}

